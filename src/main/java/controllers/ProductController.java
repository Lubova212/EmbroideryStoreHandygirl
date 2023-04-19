package controllers;

import database.ProductDB;
import javax.swing.*;

public class ProductController {

    ProductDB productDB = new ProductDB();
    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();

    public void searchForProduct() {

        String partName = registrationAndLogin.getUserInput("Please enter the name or a part of name of the product you want to find");
        productDB.findingExistingProduct(partName);
    }

    public void showAllProducts() {
        productDB.gettingProductsFromDB();
    }

    public void updateProduct() {

        String productName = registrationAndLogin.getUserInput("Please enter product name you want to update");
        String [] choice = {"productName", "weight", "info", "price", "quantity", "purchasePrice"};
        String wantToChangeMore;
        do{
        String column = (String) JOptionPane.showInputDialog(
                null,
                "Please select a column you want to change",
                "Options",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]);

        String newValue = registrationAndLogin.getUserInput("Please enter a new value");

        productDB.updatingDB(productName, column, newValue);
        wantToChangeMore = registrationAndLogin.getUserInput("Do you want to make more changes? y/n");
        }
        while (!wantToChangeMore.equals("n"));
    }

    public void deleteProduct() {
        String productName = registrationAndLogin.getUserInput("Please enter product name you want to delete");
        productDB.deletingProductFromDB(productName);

    }
}
