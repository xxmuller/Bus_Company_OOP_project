package gui;

import Data.UserData;
import IDCards.*;
import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class MyProfileController {
    @FXML
    private Label userNameLabel, userEmailLabel, userBirthdateLabel, userCategoryLabel;
    @FXML
    private Label idCardNumberLabel, idCardTypeLabel, idCardValidity;
    private String username;
    private UserData data = new UserData();
    private User user;
    private SwitchScene switchScene = new SwitchScene();
    private IDCard idCard;

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //metoda na nastavenie uzivatelovho preukazu
    public void setUserID() throws SQLException {
        user.setTypeofIDCard();
        this.idCard = user.getTypeofIDCard();
        //z db ziska cislo preukazu
        idCard.setIDCardNumber(data.getUserInfo(username, "idCard"));
        if(data.getUserInfo(username, "idCardValidity").equals("1"))
            idCard.setIdValidity(true);
        else
            idCard.setIdValidity(false);
    }
    //nastavi vsetky labely
    public void setLabelText(String username) throws SQLException {
        userNameLabel.setText(data.getUserInfo(username, "name"));
        userEmailLabel.setText(data.getUserInfo(username, "email"));
        userBirthdateLabel.setText(data.getUserInfo(username, "birthdate"));
        userCategoryLabel.setText(data.getUserInfo(username, "category"));
        idCardNumberLabel.setText(idCard.getIDCardNumber());
        if(idCard.getIdValidity()){
            idCardValidity.setText("Accepted");
            idCardValidity.setStyle("-fx-text-fill: green");
        }
        else{
            idCardValidity.setText("Not accepted");
            idCardValidity.setStyle("-fx-text-fill: red");
        }
        if(idCard instanceof ID)
            idCardTypeLabel.setText("ID Card");
        else if(idCard instanceof ISIC)
            idCardTypeLabel.setText("ISIC");
        else if(idCard instanceof ZTPCard)
            idCardTypeLabel.setText("ZTP Card");
        else if(idCard == null)
            idCardTypeLabel.setText("None");
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }

    public void buyTicketButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToBuyTicketsScene(event, username, user);
    }

    public void myTicketsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToMyTicketsScene(event, username, user);
    }

    public void myCardButtonClicked(ActionEvent event) throws IOException{
        switchScene.switchToMyCardScene(event, username, user);
    }
}
