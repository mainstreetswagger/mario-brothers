package dbcontext.repositories;

import dbcontext.interfaces.IUserRepository;
import dbcontext.models.MarioUser;

import java.sql.*;

public class UserRepository implements IUserRepository {
    private Connection connection;
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MarioUser getUser(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MarioUser user = null;
        try {
            stmt = connection.prepareStatement("select * from users m where m.id = ? limit 1;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String userName = rs.getString("name");
                String password = rs.getString("password");
                short role = rs.getShort("role");
                user = new MarioUser(id, userName, password, role);
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
    public MarioUser getUser(String name, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MarioUser user = null;
        try {
            stmt = connection.prepareStatement("select * from users where name = ? and password = ?;");
            stmt.setString(1, name);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs == null){
                return user;
            }
            while(rs.next()) {
                int id = rs.getInt("id");
                short role = rs.getShort("role");
                user = new MarioUser(id, name, password, role);
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean hasUser(String name) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MarioUser user = null;
        boolean exists = false;
        try {
            stmt = connection.prepareStatement("select u.name from users u where u.name = ? limit 1;");
            stmt.setString(1, name);

            rs = stmt.executeQuery();
            if (rs != null){
                exists = true;
            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
