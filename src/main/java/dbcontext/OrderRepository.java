package dbcontext;

import dbcontext.models.Meal;
import dbcontext.models.Order;

import java.util.ArrayList;

public class OrderRepository implements IOrderRepository{
    @Override
    public ArrayList<Order> getOrders() {
        return null;
    }

    @Override
    public Order getOrder(int orderId) {
        return null;
    }

    @Override
    public Order createOrder(Meal[] meals) {
        return null;
    }
}
