package dbcontext.repositories;

import dbcontext.enums.OrderStatus;
import dbcontext.interfaces.IOrderRepository;
import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderRepository implements IOrderRepository {
    private Connection connection;
    public OrderRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public ArrayList<Order> getOrders()  {
        ArrayList<Order> orders = new ArrayList<Order>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("select * from orders;");
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                Timestamp timestamp = rs.getTimestamp("createdAt");
                Date createdAt = new Date(timestamp.getTime());
                double priceTotal = rs.getDouble("priceTotal");
                short status = rs.getShort("status");
                orders.add(new Order(id, userId, createdAt, priceTotal, status));
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return orders;
    }

    @Override
    public Order getOrder(int orderId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order = null;
        try {
            stmt = connection.prepareStatement("select * from orders o where o.id = ? limit 1;");
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int userId = rs.getInt("userId");
                Timestamp timestamp = rs.getTimestamp("createdAt");
                Date createdAt = new Date(timestamp.getTime());
                double priceTotal = rs.getDouble("priceTotal");
                short status = rs.getShort("status");
                order = new Order(orderId, userId, createdAt, priceTotal, status);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        return order;
    }

    @Override
    public int createOrder(int userId, double total) {
        int orderId = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            String command = String.format("insert into orders (userId, priceTotal) values (%s,%s);", userId, total);
            stmt = connection.createStatement();
            int count = stmt.executeUpdate(command, Statement.RETURN_GENERATED_KEYS);
            if(count > 0) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                orderId = rs.getInt(1);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return orderId;
    }

    @Override
    public short updateStatus(int orderId) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Order order = this.getOrder(orderId);
            if(order == null)
                return 0;
            OrderStatus status = OrderStatus.values()[order.getStatus() - 1];
            OrderStatus nextStatus = null;

            if(status == OrderStatus.New)
                nextStatus = OrderStatus.InProcess;
            else if(status == OrderStatus.InProcess)
                nextStatus = OrderStatus.Ready;
            else
                return 0;

            stmt = connection.createStatement();
            String command = String.format("update orders set status = %s where id = %s;", nextStatus.getValue(), orderId);
            int count = stmt.executeUpdate(command, Statement.RETURN_GENERATED_KEYS);
            if(count > 0) {
                return nextStatus.getValue();
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public ArrayList<Order> getOrdersByUserId(int userId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("select * from orders o where o.userId = ?;");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                Timestamp timestamp = rs.getTimestamp("createdAt");
                Date createdAt = new Date(timestamp.getTime());
                double priceTotal = rs.getDouble("priceTotal");
                short status = rs.getShort("status");
                orders.add(new Order(id, userId, createdAt, priceTotal, status));
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return orders;
    }
}
