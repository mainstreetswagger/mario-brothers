package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LoginBean;
import dbcontext.DbConfiguration;

public class LoginDao {
        public int getUserID(LoginBean loginBean) throws ClassNotFoundException {
            int userID = 0;
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager
                    .getConnection(DbConfiguration.url, "root", "74524Zaur%");

                 PreparedStatement preparedStatement = connection
                         .prepareStatement("select * from users where name = ? and password = ? ")) {
                preparedStatement.setString(1, loginBean.getUsername());
                preparedStatement.setString(2, loginBean.getPassword());

                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs == null){
                    return userID;
                }
                rs.next();
                userID = rs.getInt("id");

            } catch (SQLException e) {
                printSQLException(e);
            }
            return userID;
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