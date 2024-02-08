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
    public int getCount() {
        return this.count;
    }
}
