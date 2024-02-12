package models.dao;

import dbcontext.DbConfiguration;
import dbcontext.models.Extra;
import dbcontext.models.Meal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuDao {
    public boolean addMeal(Meal meal) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(DbConfiguration.url, DbConfiguration.user, DbConfiguration.password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO meals (name, price) VALUES (?, ?)")) {
            preparedStatement.setString(1, meal.getName());
            preparedStatement.setDouble(2, meal.getPrice());
            int rowsInserted = preparedStatement.executeUpdate();
            success = rowsInserted > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return success;
    }

    public boolean addExtra(Extra extra) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(DbConfiguration.url, DbConfiguration.user, DbConfiguration.password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO extras (name, price) VALUES (?, ?)")) {
            preparedStatement.setString(1, extra.getName());
            preparedStatement.setDouble(2, extra.getPrice());
            int rowsInserted = preparedStatement.executeUpdate();
            success = rowsInserted > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return success;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
