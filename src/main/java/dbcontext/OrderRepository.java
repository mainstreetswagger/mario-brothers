package dbcontext;

import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderRepository implements IOrderRepository{
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
    public Order createOrder(Meal[] meals, int userId) {
        Order order = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            double total = 0;
            for(int i = 0; i < meals.length; i++) {
                total += meals[i].getPrice();
            }

            String command = String.format("insert into orders (userId, priceTotal) values (%s,%s);", userId, total);
            stmt = connection.createStatement();
            int count = stmt.executeUpdate(command, Statement.RETURN_GENERATED_KEYS);
            if(count > 0) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                int orderId= rs.getInt(1);
                order = this.getOrder(orderId);

            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            return order;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
