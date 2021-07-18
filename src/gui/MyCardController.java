package gui;

import Data.UserData;
import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

import java.io.IOException;
import java.sql.SQLException;

public class MyCardController {
    private User user;
    private String username;
    @FXML
    private Label creditLabel, rechargeLabel;
    @FXML
    private MenuButton chooseCreditMenu;
    private UserData data = new UserData();
    private double credit;
    private SwitchScene switchScene = new SwitchScene();

    public void setUsername(String username){
        this.username = username;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setCreditLabel(double credit){
        creditLabel.setText(credit + "€");
    }

    //ak chce uzivatel dobit 5€ na svoju kartu
    public void fiveEuroOptClicked(){
        chooseCreditMenu.setText("5€");
        this.credit = 5;
    }
    //ak chce uzivatel dobit 10€ na svoju kartu
    public void tenEuroOptClicked(){
        chooseCreditMenu.setText("10€");
        this.credit = 10;
    }
    //ak chce uzivatel dobit 25€ na svoju kartu
    public void twentyfiveEuroOptClicked(){
        chooseCreditMenu.setText("25€");
        this.credit = 25;
    }
    //ak chce uzivatel dobit 50€ na svoju kartu
    public void fiftyEuroOptClicked(){
        chooseCreditMenu.setText("50€");
        this.credit = 50;
    }

    public void rechargeButtonClicked() throws SQLException {
        if(credit != 0){
            user.setCardCredit(credit);
            //v db aktualizuje kredit uzivatela
            data.updateCredit(user.getCardCredit(), username);
            setCreditLabel(user.getCardCredit());
            System.out.println("User's credit recharged for: " + credit + "€");
            rechargeLabel.setText("Credit recharged.");
        }else
            rechargeLabel.setText("Choose credit first.");
    }

    public void myProfileButtonClicked(ActionEvent event) throws SQLException, IOException {
        switchScene.switchToMyProfileScene(event, username, user);
    }

    public void buyTicketButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToBuyTicketsScene(event, username, user);
    }

    public void myTicketsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToMyTicketsScene(event, username, user);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }
}
