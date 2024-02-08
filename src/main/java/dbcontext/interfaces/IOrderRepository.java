package dbcontext.interfaces;

import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.util.ArrayList;

public interface IOrderRepository {
    ArrayList<Order> getOrders();
    Order getOrder(int orderId);
    int createOrder(Meal[] meals, int userId, double total);
}
