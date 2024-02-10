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
import javax.servlet.http.HttpSession;
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
            request.setAttribute("title", "Meals");
            request.setAttribute("header", "Mario Brothers Menu");
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
            MealCount[] filteredMeals = new MealCount[mealCounts.length];
            double total = 0;
            for(int i = 0; i < mealCounts.length; i++) {
                Meal meal = dbContext.getMealRepository().getMeal(mealCounts[i].getMealId());
                if(meal != null) {
                    filteredMeals[i] = mealCounts[i];
                    total+=mealCounts[i].getCount() * meal.getPrice();
                }
            }
            filteredMeals = Arrays.stream(filteredMeals)
                    .filter(m -> (m != null))
                    .toArray(MealCount[]::new);

            HttpSession session = request.getSession();
            Object obj = session.getAttribute("userId");
            int userId = 1;
            if (obj != null){
                userId = Integer.parseInt(obj.toString());
            }
            int orderId = dbContext.getOrderRepository().createOrder(userId, total);
            if(orderId > 0) {
                int[] mealOrders = new int[filteredMeals.length];
                for(int i = 0; i < filteredMeals.length; i++) {
                    mealOrders[i] = dbContext.getMealOrderRepository().createMealOrder(orderId, filteredMeals[i]);
                }
                StringBuilder sb = new StringBuilder("/orders?id=").append(orderId);
                response.setStatus(301);
                response.setHeader("Location", sb.toString());
                response.setHeader("Connection", "close");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
