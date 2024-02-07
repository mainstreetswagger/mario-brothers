package dbcontext.models;

public class ExtraOrder {
    private int id;
    private int extraId;
    private int orderId;
    private int count;
    public ExtraOrder() { }
    public ExtraOrder(int id, int extraId, int orderId, int count) {
        if(id > 0)
            this.id = id;
        if(extraId > 0)
            this.extraId = extraId;
        if(orderId > 0)
            this.orderId = orderId;
        if(count > 0)
            this.count = count;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
    }
    public int getExtraId() {
        return this.extraId;
    }
    public void setExtraId(int extraId) {
        if(extraId > 0)
            this.extraId = extraId;
    }
    public int getOrderId() {
        return this.orderId;
    }
    public void setOrderId(int orderId) {
        if(orderId > 0)
            this.orderId = orderId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        if(count > 0)
            this.count = count;
    }
}
