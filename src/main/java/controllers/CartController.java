package controllers;

import database.CartDB;
import database.CustomerDB;
import database.ProductDB;
import reports.ProfitLossReport;
import java.sql.SQLException;


public class CartController {

    CustomerDB customerDB = new CustomerDB();
    ProductDB productDB = new ProductDB();
    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
    CartDB cartDB = new CartDB();

    public void addToCart() {

        String partName = registrationAndLogin.getUserInput("Please enter the name or a part of name of the product you want to find");

        productDB.choosingProductForCart(partName, registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
    }
    public void reviewTheCart() {

        cartDB.printingCart(registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
    }

    public void handlePurchase() throws SQLException {

        String option = registrationAndLogin.getUserInput("Do you want to buy items in your cart? y/n");

        if (option.equals("y")){
            if (customerDB.checkIfEnoughBalance() > 0){
                customerDB.changeBalance(registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
                productDB.updatingProductQuantity(registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
                cartDB.cleaningCart(registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
            } else {
                registrationAndLogin.displayMessage("You don't have enough money to purchase all products");
            }

        }else {
            registrationAndLogin.displayMessage("Please choose another action");
        }

    }
    public void deleteFromCart() throws SQLException {
        String partName = registrationAndLogin.getUserInput("Please enter the name or a part of name of the product you want to find");
        cartDB.deletingPositionFromCart(partName, registrationAndLogin.getUserName(), registrationAndLogin.getPassword());
    }
}
