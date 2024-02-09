package dbcontext.repositories;

import dbcontext.interfaces.IUserRepository;
import dbcontext.models.Meal;
import dbcontext.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository implements IUserRepository {
    private Connection connection;
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUser(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = connection.prepareStatement("select * from users m where m.id = ? limit 1;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                short role = rs.getShort("role");
                user = new User(id, userName, password, role);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return user;
    }
}
