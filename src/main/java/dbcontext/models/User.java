package dbcontext.models;

public class User {
    private String userName;
    private String password;

    private int Role;

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
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
