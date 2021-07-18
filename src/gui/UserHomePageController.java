package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

import Users.*;
import Data.*;

public class UserHomePageController {
    private UserData data = new UserData();
    private User user;
    private TicketDiscount ticketDiscount = new TicketDiscount();
    private String username;
    @FXML
    private Label welcomeLabel;
    private SwitchScene switchScene = new SwitchScene();

    public void setUsername(String username){
        this.username = username;
    }

    public TicketDiscount getTicketDiscount() {
        return ticketDiscount;
    }

    public User getUser() {
        return user;
    }

    public void setWelcomeLabel(String username) throws SQLException {
        this.welcomeLabel.setText("Welcome " + data.getUserInfo(username, "name"));
    }

    public void setUserCardCredit(String username) throws SQLException {
        user.setCardCredit(data.getCredit(username));
    }

    //vytvorenie usera podla kategorie, ktora je zapisana v db
    public void createUser(String username) throws SQLException {
        switch (data.getUserInfo(username, "category")){
            case "student":
                user = new Student();
                break;
            case "adult":
                user = new Adult();
                break;
            case "child":
                user = new Child();
                break;
            case "ztp":
                user = new ZTP();
                break;
            case "pension":
                user = new Pensioner();
                break;
        }
    }

    public void buyTicketButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToBuyTicketsScene(event, username, user);
    }

    public void myProfileButtonClicked(ActionEvent event) throws SQLException, IOException {
        switchScene.switchToMyProfileScene(event, username, user);
    }

    public void myTicketsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToMyTicketsScene(event, username, user);
    }

    public void myCardButtonClicked(ActionEvent event) throws IOException {
        switchScene.switchToMyCardScene(event, username, user);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }

}
