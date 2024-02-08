package dbcontext.models;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private Date createdAt;
    private double priceTotal;
    private short status;

    public Order() { }
    public Order(int id, int userId, Date createdAt, double priceTotal, short status) {
        if(id > 0)
            this.id = id;
        if(userId > 0)
            this.userId = userId;
        if(priceTotal > 0)
            this.priceTotal = priceTotal;
        this.createdAt = createdAt;
        this.status = status;
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
    public double getTotal() { return this.priceTotal; }
    public void setTotal(double total) {
        if(total > 0)
            this.priceTotal = total;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {

        this.createdAt = createdAt;
    }
    public short getStatus() {
        return this.status;
    }
    public void setStatus(short status) {
        this.status = status;
    }
}
