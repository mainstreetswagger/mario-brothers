package com.meals;

import dbcontext.MarioBrothersDBContext;
import dbcontext.models.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "my-orders", value = "/my-orders")
public class CustomerOrdersListServlet extends HttpServlet {
    private MarioBrothersDBContext dbContext;
    public CustomerOrdersListServlet() {
        dbContext = new MarioBrothersDBContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
            List<Order> orders = dbContext.getOrderRepository().getOrdersByUserId(userId);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/orders/customerOrdersList.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
