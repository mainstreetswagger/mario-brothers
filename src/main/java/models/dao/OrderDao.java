package models.dao;

import dbcontext.DbConfiguration;
import dbcontext.models.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao {
    public ArrayList<Order> getAllOrders() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Order> orders = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DbConfiguration.url, DbConfiguration.user, DbConfiguration.password);
            String query = "SELECT * FROM orders";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                Timestamp createdAt = resultSet.getTimestamp("createdAt");
                double totalPrice = resultSet.getDouble("priceTotal");
                short status = resultSet.getShort("status");

                Order order = new Order(id, userId, createdAt, totalPrice, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close resultSet, statement, and connection
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return orders;
    }

    public void updateOrderStatus(int orderId, int newStatus) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DbConfiguration.url, DbConfiguration.user, DbConfiguration.password);
            String query = "UPDATE orders SET status = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, newStatus);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close statement and connection
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}