package database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    CustomerDB customerDB = new CustomerDB();

    public int insertingNewProduct(String productName, Float weight, String info, Float price, Integer quantity, Float purchasePrice) throws SQLException {
        int rowInserted = 0;

        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "INSERT INTO product (productName, weight, info, price, quantity, purchasePrice) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, productName);
            statement.setFloat(2, weight);
            statement.setString(3, info);
            statement.setFloat(4, price);
            statement.setInt(5, quantity);
            statement.setFloat(6, purchasePrice);

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


    public void gettingProductsFromDB() {
        try {
            Connection conn = ConnectionToDB.getConnection();

            String sql = "SELECT productName, weight, info, price, quantity FROM product";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
            List<String[]> productList = new ArrayList<>();

            while (resultSet.next()) {

                String productName = resultSet.getString("productName");
                String weight = resultSet.getString("weight");
                String info = resultSet.getString("info");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String[] productData = {productName, weight, info, price, quantity};
                productList.add(productData);

                String output = "Product #%d : %s\n";
                System.out.println(String.format(output, ++count, productName));
            }

            String[] productNames = new String[productList.size()];
            for (int i = 0; i < productList.size(); i++) {
                productNames[i] = productList.get(i)[0]; // get product name
            }

            String selectedProduct = (String) JOptionPane.showInputDialog(null, "Select a product:",
                    "Product List", JOptionPane.PLAIN_MESSAGE, null, productNames, productNames[0]);

            if (selectedProduct != null) {
                // User selected a product from the dropdown
                for (String[] productData : productList) {
                    if (productData[0].equals(selectedProduct)) {
                        // Found the selected product, display additional info
                        String message = "Product Name: " + productData[0] + "\n"
                                + "Weight: " + productData[1] + "\n"
                                + "Info: " + productData[2] + "\n"
                                + "Price: " + productData[3] + "\n"
                                + "Quantity: " + productData[4];
                        JOptionPane.showMessageDialog(null, message, "Product Information", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            } else {
                // User closed the dropdown without selecting a product
                JOptionPane.showMessageDialog(null, "User cancelled the product selection.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findingExistingProduct(String partName) {
        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "SELECT * FROM product WHERE productName LIKE '%" + partName + "%'";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;

            List<String[]> productList = new ArrayList<>();

            while (resultSet.next()) {

                String productName = resultSet.getString("productName");
                String weight = resultSet.getString("weight");
                String info = resultSet.getString("info");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String[] productData = {productName, weight, info, price, quantity};
                productList.add(productData);

                String output = "Product #%d : %s\n";
                System.out.println(String.format(output, ++count, productName));
            }

            String[] productNames = new String[productList.size()];
            for (int i = 0; i < productList.size(); i++) {
                productNames[i] = productList.get(i)[0]; // get product name
            }

            String selectedProduct = (String) JOptionPane.showInputDialog(null, "Select a product:",
                    "Product List", JOptionPane.PLAIN_MESSAGE, null, productNames, productNames[0]);

            if (selectedProduct != null) {
                // User selected a product from the dropdown
                for (String[] productData : productList) {
                    if (productData[0].equals(selectedProduct)) {
                        // Found the selected product, display additional info
                        String message = "Product Name: " + productData[0] + "\n"
                                + "Weight: " + productData[1] + "\n"
                                + "Info: " + productData[2] + "\n"
                                + "Price: " + productData[3] + "\n"
                                + "Quantity: " + productData[4];
                        JOptionPane.showMessageDialog(null, message, "Product Information", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            } else {
                // User closed the dropdown without selecting a product
                JOptionPane.showMessageDialog(null, "User cancelled the product selection.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatingDB(String productName, String column, String newValue) {

        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "UPDATE product SET " + column + "= ? WHERE productName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newValue);
            statement.setString(2, productName);

            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product was updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletingProductFromDB(String productName) {

        try {
            Connection conn = ConnectionToDB.getConnection();
            String sql = "DELETE FROM product WHERE productName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, productName);

            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product was deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choosingProductForCart(String partName, String userName, String password) {

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

            String quantity1 = JOptionPane.showInputDialog(null, "Select quantity");

            String sql1 = "INSERT INTO cart (productName, weight, info, price, quantity, userID ) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setString(1, selectedProduct);
            statement1.setString(2, weight);
            statement1.setString(3, info);
            statement1.setString(4, price);
            statement1.setString(5, quantity1);
            statement1.setInt(6, customerDB.getId(userName, password));

            statement1.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updatingProductQuantity(String userName, String password) {
        try {
            Connection conn = ConnectionToDB.getConnection();

            String sql = "SELECT productName, quantity FROM cart WHERE userID = '" + customerDB.getId(userName, password) + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;

            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                Integer quantity = resultSet.getInt("quantity");

                String sql1 = "UPDATE product SET quantity = quantity - ? WHERE productName = ?";
                PreparedStatement statement1 = conn.prepareStatement(sql1);
                statement1.setInt(1, quantity);
                statement1.setString(2, productName);

                statement1.executeUpdate();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}