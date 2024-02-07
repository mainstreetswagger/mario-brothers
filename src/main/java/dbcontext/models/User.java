package dbcontext.models;

public class User {
    private int id;
    public User(){}
    public User(int id) {
        if(id > 0)
            this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setId() {
        if(id > 0)
            this.id = id;
    }
}
