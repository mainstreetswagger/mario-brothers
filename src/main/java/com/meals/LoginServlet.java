package com.meals;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import dbcontext.models.MarioUser;
import models.dao.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            MarioUser user = loginDao.getUser(loginBean);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("userRole", user.getRole());

                response.sendRedirect("meals");
            } else {
                request.setAttribute("error","There is no such a user");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}