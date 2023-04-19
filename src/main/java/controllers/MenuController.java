package controllers;

import database.CustomerDB;
import java.sql.SQLException;


public class MenuController {

    CustomerDB customerDB = new CustomerDB();
    RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();

    public void showUserInfo() throws SQLException {

        String userName = registrationAndLogin.getUserName();
        String password = registrationAndLogin.getPassword();

        customerDB.getId(userName,password);

        CustomerDB.printArray(customerDB.getInfoFromDB(userName, password));

    }

    public void addBalance() throws SQLException {

        Float depositSum = Float.valueOf(registrationAndLogin.getUserInput("Please enter the amount you want to deposit to your account"));

        String userName = registrationAndLogin.getUserName();
        String password = registrationAndLogin.getPassword();

        customerDB.addingFundsToAccount(userName, password, depositSum);

    }
}
