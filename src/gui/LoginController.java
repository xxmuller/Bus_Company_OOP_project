package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import Data.*;

public class LoginController {
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    public void loginButtonClicked(ActionEvent event) throws SQLException, IOException {
        String password, username;

        password = pf_password.getText();
        username = tf_username.getText();

        FXMLLoader userLoader = new FXMLLoader();
        FXMLLoader adminLoader = new FXMLLoader();
        userLoader.setLocation(getClass().getResource("UserHomePage.fxml"));
        adminLoader.setLocation(getClass().getResource("AdminHomePage.fxml"));
        Parent userHomepageParent = userLoader.load();
        Parent adminHomepageParent = adminLoader.load();
        Scene userHomepageScene = new Scene(userHomepageParent);

        UserHomePageController userController = userLoader.getController();
        AdminHomePageController adminController = adminLoader.getController();

        Scene adminHomepageScene = new Scene(adminHomepageParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        UserData data = new UserData();

        //ak je uzivatel tak prehodi na scenu "domovskej stranky" uzivatela
        if(data.checkUser(username, password)){
            System.out.println("User "+ username +" logged in...");
            userController.createUser(username);
            userController.setUsername(username);
            userController.setWelcomeLabel(username);
            userController.setUserCardCredit(username);
            window.setScene(userHomepageScene);
            window.show();
        }

        //ak je admin tak prehodi na scenu "domovskej stranky" admina
        if(data.checkAdmin(username, password)){
            System.out.println("Admin "+ username +" logged in...");
            adminController.createAdmin(username);
            window.setScene(adminHomepageScene);
            window.show();
        }
    }

    public void backButtClicked(ActionEvent event) throws IOException {
        Parent regWindowParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene regWindowScene = new Scene(regWindowParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(regWindowScene);
        window.show();
    }
}