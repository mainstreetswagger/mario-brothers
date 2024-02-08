package com.meals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbcontext.MarioBrothersDBContext;
import dbcontext.models.Meal;
import models.MealCount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "meals", value = "/meals")
public class MealsServlet extends HttpServlet {
    private MarioBrothersDBContext dbContext;
    public MealsServlet() {
        dbContext = new MarioBrothersDBContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Meal> meals = dbContext.getMealRepository().getMeals();
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/meals/meals.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            MealCount[] mealCounts = mapper.readValue(request.getReader(), MealCount[].class);
            Meal[] meals = new Meal[mealCounts.length];
            MealCount[] filteredMeals = new MealCount[mealCounts.length];
            double total = 0;
            for(int i = 0; i < mealCounts.length; i++) {
                Meal meal = dbContext.getMealRepository().getMeal(mealCounts[i].getMealId());
                if(meal != null) {
                    meals[i] = meal;
                    filteredMeals[i] = mealCounts[i];
                    total+=mealCounts[i].getCount() * meal.getPrice();
                }
            }
            meals = Arrays.stream(meals)
                    .filter(m -> (m != null))
                    .toArray(Meal[]::new);
            filteredMeals = Arrays.stream(filteredMeals)
                    .filter(m -> (m != null))
                    .toArray(MealCount[]::new);

            //int userId = (int)request.getAttribute("userId");

            int orderId = dbContext.getOrderRepository().createOrder(meals, 1, total);
            if(orderId > 0) {
                int[] mealOrders = new int[meals.length];
                for(int i = 0; i < filteredMeals.length; i++) {
                    mealOrders[i] = dbContext.getMealOrderRepository().createMealOrder(orderId, filteredMeals[i]);
                }
                StringBuilder sb = new StringBuilder("/orders?id=");
                sb.append(orderId);
                sb.append("&counts=");
                for(int i = 0; i < mealOrders.length; i++) {
                    if(i > 0)
                        sb.append(',');
                    sb.append(mealOrders[i]);
                }
                String path = sb.toString();
                response.setStatus(301);
                response.setHeader("Location", path);
                response.setHeader("Connection", "close");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
