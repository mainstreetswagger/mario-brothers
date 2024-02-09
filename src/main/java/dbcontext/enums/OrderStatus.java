package dbcontext.enums;

public enum OrderStatus {
    New((short)1),
    InProcess((short)2),
    Ready((short)3);
    private final short value;
    OrderStatus(short value){
        this.value = value;
    }
    public short getValue() {
        return value;
    }
}
