package gui;

import Data.UserData;
import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class MyTicketsController {
    @FXML
    private Label dayLabel, weekLabel, monthLabel;
    private User user;
    private String username;
    private UserData data = new UserData();
    private int dayTickets = 0, weekTickets = 0, monthTickets = 0;
    private SwitchScene switchScene = new SwitchScene();

    public void setUsername(String username){
        this.username = username;
    }

    public void setUser(User user){
        this.user = user;
    }

    //nastavi pocet jednotlivych listkov
    public void countTickets() throws SQLException {
        dayTickets = data.getTicketAmount("day", data.getUserInfo(username, "name"));
        weekTickets = data.getTicketAmount("week", data.getUserInfo(username, "name"));
        monthTickets = data.getTicketAmount("month", data.getUserInfo(username, "name"));
    }

    //vypise pocty listkov pre daneho uzivatela
    public void setLabelsText(){
        dayLabel.setText(dayLabel.getText() + dayTickets);
        weekLabel.setText(weekLabel.getText() + weekTickets);
        monthLabel.setText(monthLabel.getText() + monthTickets);
    }

    public void myProfileButtonClicked(ActionEvent event) throws SQLException, IOException {
        switchScene.switchToMyProfileScene(event, username, user);
    }

    public void buyTicketButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToBuyTicketsScene(event, username, user);
    }

    public void myCardButtonClicked(ActionEvent event) throws IOException {
        switchScene.switchToMyCardScene(event, username, user);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }
}
