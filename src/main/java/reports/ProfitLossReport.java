package reports;

import database.ConnectionToDB;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class ProfitLossReport {

    public void updateDailyInventoryStart() {

        String currentDate = LocalDate.now().toString();
        String sql = "INSERT INTO dailyInventory (productId, date, startQuantity, endQuantity, price, purchasePrice ) " +
                "SELECT id, '" + currentDate + "', quantity, quantity, price, purchasePrice FROM product";
        try {
            Connection conn = ConnectionToDB.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDailyInventoryEnd() {
        String currentDate = LocalDate.now().toString();

        try {
            Connection conn = ConnectionToDB.getConnection();

            String sql = "SELECT id, quantity FROM product";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String productID = resultSet.getString("id");
                Integer quantity = resultSet.getInt("quantity");

                String sql1 = "UPDATE dailyInventory SET endQuantity = '" + quantity + "' WHERE productID = '" + productID + "' AND date = '" +currentDate+ "'";

                PreparedStatement statement1 = conn.prepareStatement(sql1);
                statement1.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProfitForDate() {
        String currentDate = LocalDate.now().toString();
        Float profit = 0.0f;
        String sql = "SELECT startQuantity, endQuantity, price, purchasePrice FROM dailyInventory WHERE date = '" + currentDate + "'";

        try {
            Connection conn = ConnectionToDB.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer startQuantity = resultSet.getInt("startQuantity");
                Integer endQuantity = resultSet.getInt("endQuantity");
                Float price = resultSet.getFloat("price");
                Float purchasePrice = resultSet.getFloat("purchasePrice");

                profit += (startQuantity - endQuantity) * (price - purchasePrice);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Total profit for " + currentDate + " is " + profit);
    }
}
