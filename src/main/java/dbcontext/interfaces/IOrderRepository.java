package dbcontext.interfaces;

import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.util.ArrayList;

public interface IOrderRepository {
    ArrayList<Order> getOrders();
    Order getOrder(int orderId);
    int createOrder(int userId, double total);
    short updateStatus(int orderId);
    ArrayList<Order> getOrdersByUserId(int userId);
}
