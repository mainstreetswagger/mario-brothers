package dbcontext.models;

public class MealOrder {
    private int id;
    private int mealId;
    private int orderId;
    private int count;
    public MealOrder() { }
    public MealOrder(int id, int mealId, int orderId, int count) {
        if(id > 0)
            this.id = id;
        if(mealId > 0)
            this.mealId = mealId;
        if(orderId > 0)
            this.orderId = orderId;
        if(count > 0)
            this.count = count;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
    }
    public int getMealId() {
        return this.mealId;
    }
    public void setMealId(int mealId) {
        if(mealId > 0)
            this.mealId = mealId;
    }
    public int getOrderId() {
        return this.orderId;
    }
    public void setOrderId(int orderId) {
        if(orderId > 0)
            this.orderId = orderId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        if(count > 0)
            this.count = count;
    }
}
