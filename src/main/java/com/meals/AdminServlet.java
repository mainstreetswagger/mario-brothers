package com.meals;

import dbcontext.models.Order;
import models.dao.OrderDao;
import models.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private OrderDao orderDao;

    public void init() {
        orderDao = new OrderDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Order> orders = orderDao.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/adminPanel.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "changeOrderStatus":
                        response.sendRedirect(request.getContextPath() + "/AdminServlet");
                        break;
                    case "addNewItem":
                        response.sendRedirect(request.getContextPath() + "/AddMenuItemServlet");
                        break;
                    default:
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                }
            } else {
                String orderIdParam = request.getParameter("orderId");
                String statusParam = request.getParameter("status");
                if (orderIdParam != null && statusParam != null) {
                    handleOrderStatusUpdate(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
                }
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }




    private void handleOrderStatusUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Extract orderId and newStatus from request parameters
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String statusParam = request.getParameter("status");

        // Validate status parameter
        if (statusParam == null || statusParam.isEmpty()) {
            // Set error message
            request.setAttribute("error", "Please select a status.");
            // Fetch all orders from the database
            ArrayList<Order> orders = orderDao.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/adminPanel.jsp").forward(request, response);
            return;
        }

        int newStatus = Integer.parseInt(statusParam);

        orderDao.updateOrderStatus(orderId, newStatus);

        response.sendRedirect(request.getContextPath() + "/AdminServlet");
    }

}
