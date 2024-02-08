package dbcontext;

import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.util.ArrayList;

public interface IOrderRepository {
    ArrayList<Order> getOrders();
    Order getOrder(int orderId);
    Order createOrder(Meal[] meals, int userId);
}
