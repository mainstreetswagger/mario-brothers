package com.meals;

import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbcontext.MarioBrothersDBContext;
import dbcontext.models.Meal;
import dbcontext.models.Order;
import models.MealCount;
import models.MealOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

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
            for(int i = 0; i < mealCounts.length; i++) {
                meals[i] = dbContext.getMealRepository().getMeal(mealCounts[i].getMealId());
            }
            //int userId = (int)request.getAttribute("userId");
            Order order = dbContext.getOrderRepository().createOrder(meals, 1);
            if(order!=null) {
                String path = String.format("/orders?id=%s", order.getId());
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
