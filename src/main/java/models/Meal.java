package models;

public class Meal {
    private int id;
    private String name = "";
    private double price;
    public Meal() { }

    public Meal(int id, String name, double price) {
        if(name != null) {
            this.name = name;
        }
        this.id = id;
        this.price = price;
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
}
