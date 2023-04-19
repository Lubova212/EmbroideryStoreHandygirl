package controllers;

import database.AdminDB;
import database.CustomerDB;
import javax.swing.*;
import java.sql.SQLException;



public class RegistrationAndLogin {

    private static String userName;
    private static String password;


    public void storingRegistrationData(){
        userName = getUserInput("Please enter your User Name");
        password = getUserInput("Please enter your password");
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void registrationOfNewUser() throws SQLException {
        storingRegistrationData();
        String fullName = getUserInput("Please enter your Full Name");
        String email = getUserInput("Please enter your email");

        if (CustomerDB.registerUser(userName, password, fullName, email) == 1){
            displayMessage("Registration was successful");
        } else {
            displayMessage("Error occurred");
        }
    }

    public void loginToAccount() throws SQLException {
        storingRegistrationData();
        displayMessage(CustomerDB.isUserRegistered(userName, password));

    }

    public void loginAsAdministrator() throws SQLException {

        userName = getUserInput("Please enter your User Name");
        password = getUserInput("Please enter your password");

        displayMessage(AdminDB.adminLogin(userName, password));

    }
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(null, message);
    }
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

}
