package com.meals;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

import models.DbConfiguration;
import models.Meal;

@WebServlet(name = "meals", value = "/meals")
public class MealsServlet extends HttpServlet {
    private Statement stmt;
    private Connection con;
    private ResultSet rs;
    private Meal[] meals;
    public MealsServlet() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DbConfiguration.url,DbConfiguration.user,DbConfiguration.password);
            stmt = con.createStatement();

                rs = stmt.executeQuery("select * from optional_ingredients;");
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                }
                rs.close();

        }catch(Exception e){ System.out.println(e);}
        meals = new Meal[]
                {
                        new Meal(1, "Margherita", 8.5),
                        new Meal(2,"Garlic sauce", .5),
                        new Meal(3,"Coke, 500ml", 1),
                };
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
    }
}
