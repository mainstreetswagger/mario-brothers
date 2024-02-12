package com.meals;

import dbcontext.models.Meal;
import dbcontext.models.Extra;
import models.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddMenuItemServlet")
public class AddMenuItemServlet extends HttpServlet {
    private MenuDao menuDao;

    public void init() {
        menuDao = new MenuDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemType = request.getParameter("itemType");
        String itemName = request.getParameter("itemName");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));

        if (itemType == null || itemType.isEmpty()) {
            request.setAttribute("error", "Please select an item type.");
            request.getRequestDispatcher("/addNewItem.jsp").forward(request, response);
            return;
        }
        if (itemType != null && itemName != null && !itemName.isEmpty()) {
            if ("meal".equals(itemType)) {
                Meal meal = new Meal();
                meal.setName(itemName);
                meal.setPrice(itemPrice);
                boolean success = menuDao.addMeal(meal);
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                } else {
                    request.setAttribute("error", "Failed to add meal.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } else if ("extra".equals(itemType)) {
                Extra extra = new Extra();
                extra.setName(itemName);
                extra.setPrice(itemPrice);
                boolean success = menuDao.addExtra(extra);
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                } else {
                    request.setAttribute("error", "Failed to add extra.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item type.");
            }
        } else {
            request.setAttribute("error", "Item name is missing.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
