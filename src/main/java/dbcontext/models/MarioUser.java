package dbcontext.models;

public class MarioUser {
    public MarioUser() {
    }
    public MarioUser(int id, String userName, String password, int role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    private int id;
    private String userName;
    private String password;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
