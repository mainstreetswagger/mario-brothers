package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;

public class MarioBrothersDBContext {
    private Connection connection;
    private IMealRepository mealRepository;
    private IOrderRepository orderRepository;

    public MarioBrothersDBContext() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DbConfiguration.url,DbConfiguration.user,DbConfiguration.password);
            mealRepository = new MealRepository(connection);
            orderRepository = new OrderRepository(connection);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public IMealRepository getMealRepository() {
        return mealRepository;
    }
    public IOrderRepository getOrderRepository() { return orderRepository; }

}
