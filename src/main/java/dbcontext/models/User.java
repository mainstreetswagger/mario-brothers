package dbcontext.models;

public class User {
<<<<<<< HEAD
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
=======
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
>>>>>>> origin/master
    }
}
