package dbcontext;

import dbcontext.models.Meal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MealRepository implements IMealRepository {
    private Statement stmt;
    private ResultSet rs;
    public MealRepository(Statement stmt) {
        this.stmt = stmt;
    }
    @Override
    public ArrayList<Meal> getMeals() {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        try {
            rs = stmt.executeQuery("select * from meals;");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                meals.add(new Meal(id, name, price));
            }
        }
        catch(Exception e) { System.out.println(e); }
        return meals;
    }


    @Override
    public Meal getMeal(int id) {
        return null;
    }

    public void close() {
        try {
        if(rs != null)
            rs.close();
        if(stmt != null)
            stmt.close();;
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
