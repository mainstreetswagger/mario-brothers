package com.meals;

import dbcontext.MarioBrothersDBContext;
import dbcontext.models.Meal;
import dbcontext.models.Order;
import dbcontext.models.User;
import models.MealReport;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "orders", urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {
    private MarioBrothersDBContext dbContext;
    public OrdersServlet() {
        dbContext = new MarioBrothersDBContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = dbContext.getOrderRepository().getOrder(id);
            if(order != null) {
                User user = dbContext.getUserRepository().getUser(order.getUserId());
                List<MealReport> mealReports = dbContext.getMealOrderRepository().getMealReportsByOrderId(order.getId());
                request.setAttribute("reports", mealReports);
                request.setAttribute("order", order);
                request.setAttribute("user", user);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        int id = Integer.parseInt(request.getParameter("id"));

        request.getRequestDispatcher("/orders/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
