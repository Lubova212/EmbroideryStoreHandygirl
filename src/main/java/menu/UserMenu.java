package menu;

import controllers.CartController;
import controllers.MenuController;
import controllers.ProductController;
import controllers.RegistrationAndLogin;
import java.sql.SQLException;

public class UserMenu {

     MenuController menuController = new MenuController();
     ProductController productController = new ProductController();
     CartController cartController = new CartController();
    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
    public void getChoices() throws SQLException {

        String choice = registrationAndLogin.getUserInput("Please enter operation you want to perform:\n" +
                "1. Show user information.\n" +
                "2. Replenish the balance.\n" +
                "3. Search for product.\n" +
                "4. Show all products.\n" +
                "5. Add product to cart.\n" +
                "6. Review of cart.\n" +
                "7. Delete product from cart\n" +
                "8. Purchase.\n" +
                "9. Exit");


    switch (choice) {
        case "1":
            menuController.showUserInfo();
            break;
        case "2":
            menuController.addBalance();
            break;
        case "3":
            productController.searchForProduct();
            break;
        case "4":
            productController.showAllProducts();
            break;
        case "5":
            cartController.addToCart();
            break;
        case "6":
            cartController.reviewTheCart();
            break;
        case "7":
           cartController.deleteFromCart();
           break;
        case "8":
            cartController.handlePurchase();
            break;
        case "9":
            System.exit(0);
            break;
        default:
            registrationAndLogin.displayMessage("Please choose an option from 1 to 9.");
    }

    getChoices();

}
    }

