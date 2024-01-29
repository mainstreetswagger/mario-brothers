package models;

public enum MealType {
    Pizza(1),
    Ingredient(2);
    private int id;
    MealType(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
}
