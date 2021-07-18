package gui;

import Admins.Admin;
import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

//trieda, v ktorej sa prehadzuju sceny
public class SwitchScene {

    public void switchToMyCardScene(ActionEvent event, String username, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyCard.fxml"));
        Parent myCardParent = loader.load();
        Scene myCardScene = new Scene(myCardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        MyCardController myCardController = loader.getController();

        myCardController.setUser(user);
        myCardController.setUsername(username);
        myCardController.setCreditLabel(user.getCardCredit());
        window.setScene(myCardScene);
        window.show();
    }

    public void switchToMyTicketsScene(ActionEvent event, String username, User user) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyTickets.fxml"));
        Parent myTicketsParent = loader.load();
        Scene myTicketsScene = new Scene(myTicketsParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        MyTicketsController myTicketsController = loader.getController();

        myTicketsController.setUser(user);
        myTicketsController.setUsername(username);
        myTicketsController.countTickets();
        myTicketsController.setLabelsText();
        window.setScene(myTicketsScene);
        window.show();
    }

    public void switchToMyProfileScene(ActionEvent event, String username, User user) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyProfile.fxml"));
        Parent myProfileParent = loader.load();
        Scene myProfileScene = new Scene(myProfileParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        MyProfileController myProfileController = loader.getController();

        myProfileController.setUser(user);
        myProfileController.setUsername(username);
        myProfileController.setUserID();
        myProfileController.setLabelText(username);
        window.setScene(myProfileScene);
        window.show();
    }

    public void switchToBuyTicketsScene(ActionEvent event, String username, User user) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BuyTickets.fxml"));
        Parent buyTicketsParent = loader.load();
        Scene buyTicketsScene = new Scene(buyTicketsParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        BuyTicketsController buyTicketsController = loader.getController();
        UserHomePageController userHome = new UserHomePageController();

        buyTicketsController.setUser(user);
        buyTicketsController.setUsername(username);
        buyTicketsController.accept(userHome.getTicketDiscount());
        window.setScene(buyTicketsScene);
        window.show();
    }

    public void switchToShowUsers(ActionEvent event, Admin admin) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowUsers.fxml"));
        Parent showUsersParent = loader.load();
        Scene showUsersScene = new Scene(showUsersParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        ShowUsersController showUsersController = loader.getController();

        showUsersController.setAdmin(admin);
        showUsersController.initialize();
        window.setScene(showUsersScene);
        window.show();
    }

    public void switchToShowAdmins(ActionEvent event, Admin admin) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowAdmins.fxml"));
        Parent showAdminsParent = loader.load();
        Scene showAdminsScene = new Scene(showAdminsParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        ShowAdminsController showAdminsController = loader.getController();
        showAdminsController.setAdmin(admin);
        showAdminsController.initialize();
        window.setScene(showAdminsScene);
        window.show();
    }

    public void switchToShowTickets(ActionEvent event, Admin admin) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowTickets.fxml"));
        Parent showTicketsParent = loader.load();
        Scene showTicketsScene = new Scene(showTicketsParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        ShowTicketsController showTicketsController = loader.getController();
        showTicketsController.setAdmin(admin);
        window.setScene(showTicketsScene);
        window.show();
    }

    public void switchToManageUsers(ActionEvent event, Admin admin) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageID.fxml"));
        Parent manageUsersParent = loader.load();
        Scene manageUsersScene = new Scene(manageUsersParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        ManageIDController manageIDController = loader.getController();

        manageIDController.setAdmin(admin);
        manageIDController.initialize();
        window.setScene(manageUsersScene);
        window.show();
    }

    public void logOut(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void logIn(ActionEvent event) throws IOException {
        Parent loginSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginSceneParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }
}
