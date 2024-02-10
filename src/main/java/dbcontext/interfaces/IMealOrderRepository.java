package dbcontext.interfaces;

import dbcontext.models.MealOrder;
import models.MealCount;
import models.MealReport;

import java.util.List;

public interface IMealOrderRepository {
    List<MealOrder> getMealOrders();
    MealOrder getMealOrder(int id);
    MealReport getMealReport(int mealOrderId);
    List<MealReport> getMealReportsByOrderId(int orderId);
    int createMealOrder(int orderId, MealCount meal);
}
