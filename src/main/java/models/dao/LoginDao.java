package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LoginBean;
import dbcontext.DbConfiguration;
import dbcontext.MarioBrothersDBContext;
import dbcontext.models.MarioUser;

public class LoginDao {
    private MarioBrothersDBContext dbContext = new MarioBrothersDBContext();
        public MarioUser getUser(LoginBean loginBean) throws ClassNotFoundException {

            return dbContext.getUserRepository().getUser(loginBean.getUsername(), loginBean.getPassword());
        }
    public int getUserID(String username) throws ClassNotFoundException {
        int userID = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(DbConfiguration.url, "root", "74524Zaur%");
             PreparedStatement preparedStatement = connection.prepareStatement("select id from users where name = ?")) {

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                userID = rs.getInt("id");
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return userID;
    }

        private void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }