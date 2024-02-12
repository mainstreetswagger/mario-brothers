package com.meals;

import dbcontext.models.MarioUser;
import models.dao.RegisterDao;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private String message;
    private RegisterDao userDao = new RegisterDao();

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().append("Served at: ").append(request.getContextPath());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("name");
        String password = request.getParameter("password");

        MarioUser user = new MarioUser();
        user.setUserName(userName);
        user.setPassword(password);

        try {
            userDao.registerUser(user);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/registerDetail.jsp");
        dispatcher.forward(request, response);    }

    public void destroy() {
    }
}