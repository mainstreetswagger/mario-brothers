package models;

public class Meal {
    private int id;
    private String name = "";
    private double price;

    private MealType mealType;
    public Meal() { }

    public Meal(int id, String name, double price, MealType mealType) {
        if(name != null) {
            this.name = name;
        }
        this.id = id;
        this.price = price;
        this.mealType = mealType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    public MealType getMealType() {
        return this.mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
}
