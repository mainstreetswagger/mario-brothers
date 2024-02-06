package dtos;

import dtos.MealOrder;

public class Order {
    private MealOrder[] meals;
    public Order() {
        this.meals = new MealOrder[0];
    }
    public Order(MealOrder[] meals) {
        if(meals != null) {
            this.meals = meals;
        } else {
            this.meals = new MealOrder[0];
        }
    }
    public MealOrder[] getMealOrders() {
        return this.meals;
    }
    public void setMealOrders(MealOrder[] meals) {
        this.meals = meals;
    }
}
