package menu;

import controllers.AdminController;
import controllers.ProductController;
import controllers.RegistrationAndLogin;
import reports.ProfitLossReport;
import java.sql.SQLException;

public class AdminMenu{

    public void getAdminOptions() throws SQLException {
        ProfitLossReport profitLossReport = new ProfitLossReport();
        RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
        AdminController adminController = new AdminController();
        ProductController productController = new ProductController();

        String choice = registrationAndLogin.getUserInput("Please, select action: \n" +
                "1. Add new product.\n" +
                "2. Review all products.\n" +
                "3. Change product.\n" +
                "4. Delete product.\n" +
                "5. Create starting report for today. \n" +
                "6. Create ending report for today.\n" +
                "7. Generate ProfitLoss report.\n" +
                "8. Exit.");

            switch (choice){
                case "1":
                    adminController.addNewProduct();
                    break;
                case "2":
                    productController.showAllProducts();
                    break;
                case "3":
                    productController.updateProduct();
                    break;
                case "4":
                    productController.deleteProduct();
                    break;
                case "5":
                    profitLossReport.updateDailyInventoryStart();
                    break;
                case "6":
                    profitLossReport.updateDailyInventoryEnd();
                    break;
                case "7":
                    profitLossReport.getProfitForDate();
                    break;
                case "8":
                    System.exit(0);
                    break;
                default:
                    registrationAndLogin.displayMessage("Please choose an option from 1 to 8.");
            }
            getAdminOptions();
        }
    }


