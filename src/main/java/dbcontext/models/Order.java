package dbcontext.models;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Timestamp createdAt;
    private double total;
    private int userId;

    public Order() { }
    public Order(int id, Timestamp createdAt, double total, int userId) {
        if(id > 0)
            this.id = id;
        if(userId > 0)
            this.userId = userId;
        if(total > 0)
            this.total = total;
        this.createdAt = createdAt;
    }
    public int getId() { return this.id; }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
    }
    public int getUserId() { return this.userId; }
    public void setUserId(int userId) {
        if(userId > 0)
            this.userId = userId;
    }
    public double getTotal() { return this.total; }
    public void setTotal(double total) {
        if(total > 0)
            this.total = total;
    }
    public Timestamp getCreatedAt() { return this.createdAt; }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
