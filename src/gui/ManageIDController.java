package gui;

import Admins.Admin;
import Admins.CEO;
import Admins.Editor;
import Admins.Secretary;
import Data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ManageIDController {
    private SwitchScene switchScene = new SwitchScene();
    private Admin admin;
    private UserData data = new UserData();
    @FXML
    private Label acceptedLabel, cardTypeLabel, wrngAdminLabel, checkLabel, idTypeLabel, idCardLabel;
    @FXML
    private TextField username_tf;
    @FXML
    private Button acceptButton, checkCardButton;
    private String username, userCategory;

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    //metoda, ktora nastavi, ktore labely a buttony sa maju zobrazit a ktore nie
    public void initialize() {
        //kontrolovat preukazy uzivatelov moze len sekretarka
        if(admin instanceof CEO || admin instanceof Editor){
            wrngAdminLabel.setVisible(true);
            checkCardButton.setVisible(false);
            checkLabel.setVisible(false);
            idCardLabel.setVisible(false);
            username_tf.setVisible(false);
            idTypeLabel.setVisible(false);
            cardTypeLabel.setVisible(false);
        }
        else{
            wrngAdminLabel.setVisible(false);
        }
        acceptButton.setVisible(false);
        acceptedLabel.setVisible(false);
    }

    //ak chce sekretarka skontrolovat preukaz uzivatela
    public void checkCardButtonClicked() throws SQLException {
        acceptButton.setVisible(false);
        acceptedLabel.setVisible(false);
        username = username_tf.getText();
        //vypise cislo preukazu
        idCardLabel.setText(data.getUserInfo(username, "idCard"));
        userCategory = data.getUserInfo(username, "category");
        //pomocou metody v (Secretary)admin zisti ci je preukaz nejakeho typu
        cardTypeLabel.setText(((Secretary) admin).getTypeofIDCard(idCardLabel.getText(), userCategory));

        //zistujem ci uz ma uzivatel overeny preukaz
        if(data.getUserInfo(username, "idCardValidity").equals("0")){
            acceptButton.setVisible(true);
        }
        else{
            acceptedLabel.setVisible(true);
        }
    }

    //metoda, ktora overi preukaz uzivatela
    public void acceptCardButtonClicked() throws SQLException {
        data.updateIDCard(username_tf.getText());
        acceptButton.setVisible(false);
        acceptedLabel.setText("ID Card accepted");
        acceptedLabel.setVisible(true);
    }

    public void showUsersButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowUsers(event, admin);
    }

    public void showAdminsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowAdmins(event, admin);
    }

    public void showTicketsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowTickets(event, admin);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }
}
