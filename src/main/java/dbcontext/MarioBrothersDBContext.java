package dbcontext;

import dbcontext.interfaces.IMealOrderRepository;
import dbcontext.interfaces.IMealRepository;
import dbcontext.interfaces.IOrderRepository;
import dbcontext.interfaces.IUserRepository;
import dbcontext.repositories.MealOrderRepository;
import dbcontext.repositories.MealRepository;
import dbcontext.repositories.OrderRepository;
import dbcontext.repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;

public class MarioBrothersDBContext {
    private Connection connection;
    private IMealRepository mealRepository;
    private IOrderRepository orderRepository;
    private IMealOrderRepository mealOrderRepository;
    private IUserRepository userRepository;

    public MarioBrothersDBContext() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DbConfiguration.url,DbConfiguration.user,DbConfiguration.password);
            mealRepository = new MealRepository(connection);
            orderRepository = new OrderRepository(connection);
            mealOrderRepository = new MealOrderRepository(connection);
            userRepository = new UserRepository(connection);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public IMealRepository getMealRepository() {
        return mealRepository;
    }
    public IOrderRepository getOrderRepository() {
        return orderRepository;
    }
    public IMealOrderRepository getMealOrderRepository() {
        return mealOrderRepository;
    }
    public IUserRepository getUserRepository() {
        return userRepository;
    }

}
