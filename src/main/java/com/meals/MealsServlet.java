package com.meals;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import models.Meal;
import models.MealType;

@WebServlet(name = "meals", value = "/meals")
public class MealsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal[] meals = new Meal[]
                {
                        new Meal(1, "Margherita", 8.5),
                        new Meal(2,"Garlic sauce", .5),
                        new Meal(3,"Coke, 500ml", 1),
                };
        try {
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/meals/meals.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
