package models;

public class MealCount {
    private int mealId;
    private int count;
    public MealCount() { }
    public MealCount(int mealId, int count) {
        this.mealId = mealId;
        this.count = count;
    }
    public int getMealId() {
        return this.mealId;
    }
    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
