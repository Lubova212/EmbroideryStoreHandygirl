package menu;

import controllers.RegistrationAndLogin;
import java.sql.SQLException;


public class Menu {
    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
    UserMenu userMenu = new UserMenu();

    AdminMenu adminMenu = new AdminMenu();

    public void menu() throws SQLException {

        String chosenOption = registrationAndLogin.getUserInput("Welcome! Please select action! \n"+
                "For registration, press 1\n"+
                "For login, press 2\n"+
                "For login as administrator, press 3");


        if (chosenOption.equals("1")) {
            registrationAndLogin.registrationOfNewUser();
            userMenu.getChoices();
        } else if (chosenOption.equals("2")) {
            registrationAndLogin.loginToAccount();
            userMenu.getChoices();
        } else if (chosenOption.equals("3")) {
            registrationAndLogin.loginAsAdministrator();
            adminMenu.getAdminOptions();
        } else {
            registrationAndLogin.displayMessage("Please enter a correct range from 1 to 3");
        }
    }


}
