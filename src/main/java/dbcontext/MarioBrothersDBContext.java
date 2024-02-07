package dbcontext;

import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class MarioBrothersDBContext {
    private Statement stmt;
    private Connection con;

    private IMealRepository mealRepository;

    public MarioBrothersDBContext() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DbConfiguration.url,DbConfiguration.user,DbConfiguration.password);
            stmt = con.createStatement();
            mealRepository = new MealRepository(stmt);

        }catch(Exception e){ System.out.println(e);}
    }
    public IMealRepository getMealRepository() {
        return mealRepository;
    }

    public void close() {
        try {
            this.mealRepository.close();
        } catch(Exception e) {
            System.out.println(e);
        }

    }
}
