package database;


import controllers.RegistrationAndLogin;
import javax.swing.*;
import java.sql.*;

public class CustomerDB {

    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();

    public static int registerUser(String userName, String password, String fullName, String email) throws SQLException {
        int rowInserted = 0;

        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "INSERT INTO user (userName, password, fullName, email) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, password);
            statement.setString(3, fullName);
            statement.setString(4, email);

            rowInserted = statement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (rowInserted > 0) {
            return 1;
        } else {
            return 0;
        }
    }


    public static String isUserRegistered(String userName, String password) throws SQLException {
        ResultSet resultSet = null;
        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "SELECT * FROM user WHERE userName = '" + userName + "' AND password = '" + password + "'";
            Statement statement = conn.createStatement();
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

    public  Object[] getInfoFromDB(String userName, String password) {
        Object[] userInfo = null;
        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "SELECT userName, fullName, email, balance FROM user WHERE id = '" + getId(userName, password) + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            userInfo = new Object[columns];
            if (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    userInfo[i - 1] = resultSet.getObject(i);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }

    public static void printArray(Object[] userInfo) {
        String[] descriptions = {"User Name: ", "Full Name: ", "email: ", "Balance: "};
        String message = "";
        for (int i = 0; i < userInfo.length; i++) {
           message += descriptions[i] + " " + userInfo[i] + "\n";

        }
        JOptionPane.showMessageDialog(null, message);
    }

    public int getId(String userName, String password) throws SQLException {
        ResultSet resultSet = null;
        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "SELECT id FROM user WHERE username = '" + userName + "' AND password = '" + password + "'";
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    public void addingFundsToAccount(String userName,String password, float depositSum) throws  SQLException {
        float newBalance = getBalance(userName, password) + depositSum;
        Connection conn = ConnectionToDB.getConnection();
        String sql = "UPDATE user SET balance = ?  WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setFloat(1, newBalance);
        statement.setInt(2, getId(userName,password));
        int rowInserted = statement.executeUpdate();

        if (rowInserted > 0) {
            registrationAndLogin.displayMessage("Your account was deposited for " + depositSum + " EUR");
        } else {
            registrationAndLogin.displayMessage("Unable to proceed request. Try again later");
        }
    }


    public float getBalance(String userName, String password) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        String sql = "SELECT balance FROM user WHERE id = '" + getId(userName,password) + "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            return resultSet.getFloat("balance");
        } else {
            return 0;
        }
    }

    public Float checkIfEnoughBalance() throws SQLException {
        Float purchasePrice = CartDB.getCartTotal(registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
        Float newBalance = getBalance(registrationAndLogin.getUserName(), registrationAndLogin.getPassword()) - purchasePrice;

        return newBalance;
    }

    public void changeBalance(String userName,String password) throws SQLException {
        Float newBalance = checkIfEnoughBalance();
        Connection conn = ConnectionToDB.getConnection();
        String sql = "UPDATE user SET balance = ?  WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setFloat(1, newBalance);
        statement.setInt(2, getId(userName,password));
        int rowInserted = statement.executeUpdate();

        if (rowInserted < 0) {
            registrationAndLogin.displayMessage("Unable to proceed request. Try again later");
        }
    }
    }
