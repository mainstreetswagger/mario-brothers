package dtos;

public class MealOrder {
    private int mealId;
    private int quantity;
    public MealOrder() { }
    public MealOrder(int mealId, int quantity) {
        this.mealId = mealId;
        this.quantity = quantity;
    }

    public int getMealId() {
        return this.mealId;
    }
    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
