package gui;

import Admins.*;
import Data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;

public class AdminHomePageController
{
    @FXML
    private Label adminTypeLabel;
    private SwitchScene switchScene = new SwitchScene();
    private UserData data = new UserData();
    private Admin admin;

    //vytvori typ admina podla toho aku ma kategoriu v db
    public void createAdmin(String username) throws SQLException {
        switch (data.getAdminInfo(username, "category")){
            case "ceo":
                admin = new CEO();
                adminTypeLabel.setText(adminTypeLabel.getText() + " CEO");
                break;
            case "editor":
                admin = new Editor();
                adminTypeLabel.setText(adminTypeLabel.getText() + " Editor");
                break;
            case "secretary":
                admin = new Secretary();
                adminTypeLabel.setText(adminTypeLabel.getText() + " Secretary");
                break;
        }
    }

    public void showUsersButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowUsers(event, admin);
    }

    public void showAdminsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowAdmins(event, admin);
    }

    public void manageUsersButtonClicked(ActionEvent event) throws IOException {
        switchScene.switchToManageUsers(event, admin);
    }

    public void showTicketsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowTickets(event, admin);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }
}
