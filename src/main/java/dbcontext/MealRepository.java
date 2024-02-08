package dbcontext;

import dbcontext.models.Meal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MealRepository implements IMealRepository {
    private Connection connection;
    public MealRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public ArrayList<Meal> getMeals() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Meal> meals = new ArrayList<Meal>();
        try {
            stmt = connection.prepareStatement("select * from meals;");
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                meals.add(new Meal(id, name, price));
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return meals;
    }


    @Override
    public Meal getMeal(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Meal meal = null;
        try {
            stmt = connection.prepareStatement("select * from meals m where m.id = ? limit 1;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                meal = new Meal(id, name, price);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return meal;
    }
}
