package dbcontext.repositories;

import dbcontext.DbConfiguration;
import dbcontext.interfaces.IUserRepository;
import dbcontext.models.Meal;
import dbcontext.models.User;

import java.sql.*;

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
                String userName = rs.getString("name");
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

    @Override
    public User getUser(String name, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = connection.prepareStatement("select * from users where name = ? and password = ? ");
            stmt.setString(1, name);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs == null){
                return user;
            }
            while(rs.next()) {
                int id = rs.getInt("id");
                short role = rs.getShort("role");
                user = new User(id, name, password, role);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            System.out.println(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
