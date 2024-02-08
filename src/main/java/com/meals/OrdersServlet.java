package com.meals;

import dbcontext.MarioBrothersDBContext;
import dbcontext.models.Meal;
import dbcontext.models.Order;
import models.MealReport;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
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
            String counts = request.getParameter("counts");
            MealReport[] reports = null;
            if(counts != null && counts != "") {
                String[] reportArr = counts.split(",");
                reports = new MealReport[reportArr.length];
                for(int i = 0; i < reportArr.length; i++) {
                    int reportId = Integer.parseInt(reportArr[i]);
                    reports[i] = dbContext.getMealOrderRepository().getMealReport(reportId);
                }
            }
            request.setAttribute("reports", reports);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/orders/order.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        int id = Integer.parseInt(request.getParameter("id"));

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
