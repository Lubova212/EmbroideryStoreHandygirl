package database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CartDB {

    static CustomerDB customerDB = new CustomerDB();

    public void printingCart(String userName, String password){
        String message = "";
        try {
            Connection conn = ConnectionToDB.getConnection();

            String sql = "SELECT productName, weight, info, price, quantity FROM cart WHERE userID = '" + customerDB.getId(userName, password) + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;

            while (resultSet.next()) {

                String productName = resultSet.getString("productName");
                String weight = resultSet.getString("weight");
                String info = resultSet.getString("info");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");

                message += "Item: \n" + productName + "\n" + "price: " + price + "\n" + "quantity: " +quantity + "\n";

                }
            JOptionPane.showMessageDialog(null, message);
        }catch (Exception e){
        e.printStackTrace();
        }
    }


    public void cleaningCart(String userName, String password) {

        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "DELETE FROM cart WHERE userID = '" + customerDB.getId(userName, password) + "'";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletingPositionFromCart(String partName, String userName, String password) throws SQLException {

        PreparedStatement statement1 = null;
        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "SELECT * FROM product WHERE productName LIKE '%" + partName + "%'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<String[]> productList = new ArrayList<>();

            int count = 0;

            String weight = null;
            String info = null;
            String price = null;
            while (resultSet.next()) {

                String productName = resultSet.getString("productName");
                weight = resultSet.getString("weight");
                info = resultSet.getString("info");
                price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String[] productData = {productName, weight, info, price, quantity};
                productList.add(productData);
            }

            String[] productNames = new String[productList.size()];
            for (int i = 0; i < productList.size(); i++) {
                productNames[i] = productList.get(i)[0]; // get product name
            }

            String selectedProduct = (String) JOptionPane.showInputDialog(null, "Select a product:",
                    "Product List", JOptionPane.PLAIN_MESSAGE, null, productNames, productNames[0]);

            String sql1 = "DELETE FROM cart WHERE productName LIKE '%" + selectedProduct + "%' AND userID = '" + customerDB.getId(userName, password) + "'";
            statement1 = conn.prepareStatement(sql1);


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (statement1.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Product was deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }

    public static Float getCartTotal(String userName, String password) {
        Float totalBalance = 0.0f;
        try {
            Connection conn = ConnectionToDB.getConnection();

            String sql = "SELECT price, quantity FROM cart WHERE userID = '" + customerDB.getId(userName, password) + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;

            while (resultSet.next()) {

                Float price = resultSet.getFloat("price");
                Integer quantity = resultSet.getInt("quantity");

                totalBalance += (price * quantity);

            }
            System.out.println(totalBalance);
            }catch (Exception e){
                e.printStackTrace();
            }
       return totalBalance;
    }
}
