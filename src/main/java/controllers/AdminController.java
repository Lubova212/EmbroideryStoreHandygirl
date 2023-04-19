package controllers;

import database.ProductDB;
import dto.Product;
import java.sql.SQLException;

public class AdminController {

    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
    Product product = new Product();
    ProductDB productDB = new ProductDB();

    public void addNewProduct() throws SQLException {

        product.setProductName(registrationAndLogin.getUserInput("Enter name of a product"));
        product.setWeight(Float.valueOf(registrationAndLogin.getUserInput("Enter product weight")));
        product.setPrice(Float.valueOf(registrationAndLogin.getUserInput("Enter product price")));
        product.setInfo(registrationAndLogin.getUserInput("Enter description"));
        product.setQuantity(Integer.valueOf(registrationAndLogin.getUserInput("Enter product quantity")));
        product.setPurchasePrice(Float.valueOf(registrationAndLogin.getUserInput("Enter product purchase price")));

        if (productDB.insertingNewProduct(product.getProductName(), product.getWeight(), product.getInfo(), product.getPrice(), product.getQuantity(), product.getPurchasePrice()) == 1){
            registrationAndLogin.displayMessage("Product inserted successfully");
        }else {
            registrationAndLogin.displayMessage("Error occurred");
        }
    }

    }

