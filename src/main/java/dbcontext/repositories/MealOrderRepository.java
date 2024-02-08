package dbcontext.repositories;

import dbcontext.interfaces.IMealOrderRepository;
import dbcontext.models.MealOrder;
import models.MealCount;
import models.MealReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class MealOrderRepository implements IMealOrderRepository {
    private Connection connection;
    public MealOrderRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<MealOrder> getMealOrders() {
        return null;
    }

    @Override
    public MealOrder getMealOrder(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MealOrder mealOrder = null;
        try {
            stmt = connection.prepareStatement("select * from mealorders m where m.id = ? limit 1;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int mealId = rs.getInt("mealId");
                int orderId = rs.getInt("orderId");
                int count = rs.getInt("count");
                mealOrder = new MealOrder(id, mealId, orderId, count);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return mealOrder;
    }

    @Override
    public MealReport getMealReport(int mealOrderId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MealReport report = null;
        try {
            stmt = connection.prepareStatement("select mo.count, m.name, m.price from mealorders mo" +
                    " inner join meals m on mo.mealId = m.id " +
                    "where mo.id = ? limit 1;");
            stmt.setInt(1, mealOrderId);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int count = rs.getInt("count");
                double price = rs.getDouble("price");
                String name = rs.getString("name");
                report = new MealReport(name, price, count);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return report;
    }

    @Override
    public int createMealOrder(int orderId, MealCount meal) {
        Statement stmt = null;
        ResultSet rs = null;
        int mealOrderId = 0;
        try {
            String command = String.format("insert into mealorders (orderId, mealId, count) values (%s, %s, %s);", orderId, meal.getMealId(), meal.getCount());
            stmt = connection.createStatement();
            int record = stmt.executeUpdate(command, Statement.RETURN_GENERATED_KEYS);
            if(record > 0) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                mealOrderId = rs.getInt(1);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return mealOrderId;
    }
}
