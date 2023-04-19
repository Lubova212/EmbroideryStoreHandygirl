package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDB {
    public static String adminLogin(String userName, String password) throws SQLException {
        ResultSet resultSet = null;
        Connection connection;
        try {
            connection = ConnectionToDB.getConnection();
            String sql = "SELECT * FROM admin WHERE userName = '" + userName + "' AND password = '" + password + "'";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resultSet.next()) {
            return "You have logged in successfully";
        } else {
            return "Wrong username or password";
        }
    }
    }
