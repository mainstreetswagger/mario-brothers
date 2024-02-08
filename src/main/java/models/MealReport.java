package models;

public class MealReport {
    private String mealName;
    private double price;
    private int count;
    public MealReport(String mealName, double price, int count) {
        this.mealName = mealName;
        this.price = price;
        this.count = count;
    }
    public String getMealName() {
        return this.mealName;
    }
    public double getPrice() {
        return this.price;
    }
    public int getCount() {
        return this.count;
    }
}
