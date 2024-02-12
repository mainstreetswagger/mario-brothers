package models.dao;

import dbcontext.DbConfiguration;
import dbcontext.models.MarioUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    public int registerUser(MarioUser user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO mariobrothers.users" +
                " (name, password, role) VALUES (?, ?, ?)";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(DbConfiguration.url, DbConfiguration.user, DbConfiguration.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, 1);
            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
