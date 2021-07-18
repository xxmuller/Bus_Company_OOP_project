package gui;

import Data.UserData;
import Users.Discount;
import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class BuyTicketsController{
    @FXML
    private Label priceLabel, confirmLabel, discountLabel;
    private double oneDayTicketPrice = 2;
    private double weekTicketPrice = 8;
    private double monthTicketPrice = 25;
    private double totalPrice, discount;
    private double discountPercentage, credit;
    private String ticket, username;
    private UserData data = new UserData();
    private User user;
    private SwitchScene switchScene = new SwitchScene();

    //urcovanie ci ma pouzivatel pravo na zlavu
    //podla toho ci ma overeny preukaz alebo nie
    public void setDiscount(double discount) throws SQLException {
        if(data.getUserInfo(username, "idCardValidity").equals("1"))
            this.discount = discount;
        else
            this.discount = 1;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void accept(Discount visitor) throws SQLException {
        user.accept(visitor);
        visitor.visit(this);
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicketType(String ticket) {
        this.ticket = ticket;
    }

    public void buyButtonClicked() throws SQLException {
        //ak si uzivatel vybral nejaky listok a ma nan dostatok kreditu
        //tak ulozi dany listok pre uzivatela do databazy s danou zlavou
        if((user.getCardCredit() - totalPrice >= 0) && (getTicket() != null)){
            confirmLabel.setText("Ticket bought!");
            data.addTicket(username, getTicket(), discountPercentage);
            //pri kupe listka znizi kredit uzivatela o cenu listka
            user.setCardCredit(-totalPrice);
            credit = user.getCardCredit();
            BigDecimal bd = new BigDecimal(credit).setScale(2, RoundingMode.HALF_UP);
            double credit2 = bd.doubleValue();
            //aktualizovanie kreditu uzivatela v db
            data.updateCredit(credit2, username);
            System.out.println("Ticket for one " + getTicket() + " bought");
            System.out.println("User's card credit is: " + credit2 + "€");
        }
        else if (user.getCardCredit() - totalPrice < 0){
            confirmLabel.setText("Not enough credit!");
        }
        else if (getTicket() == null){
            confirmLabel.setText("Select a ticket!");
        }
    }

    public void setDiscountPercentage(double discount) {
        this.discountPercentage = 100-discount*100;
    }

    public void oneDayTicketButtonClicked(){
        setTicketType("day");
        user.setTicketPrice(oneDayTicketPrice);
        totalPrice = user.getTicketPrice() * discount;
        setDiscountPercentage(discount);
        showTicketInfo(oneDayTicketPrice, totalPrice, discountPercentage);
    }

    public void weekTicketButtonClicked(){
        setTicketType("week");
        user.setTicketPrice(weekTicketPrice);
        totalPrice = user.getTicketPrice() * discount;
        setDiscountPercentage(discount);
        showTicketInfo(weekTicketPrice, totalPrice, discountPercentage);
    }

    public void monthTicketButtonClicked(){
        setTicketType("month");
        user.setTicketPrice(monthTicketPrice);
        totalPrice = user.getTicketPrice() * discount;
        setDiscountPercentage(discount);
        showTicketInfo(monthTicketPrice, totalPrice, discountPercentage);
    }

    public void showTicketInfo(double normalPrice, double totalPrice, double discount){
        discountLabel.setText("Price of the ticket is: " + normalPrice +"€");
        priceLabel.setText("Your price is: " + totalPrice + "€ (discount " + discount + "%)");
    }

    public void myProfileButtonClicked(ActionEvent event) throws SQLException, IOException {
        switchScene.switchToMyProfileScene(event, username, user);
    }

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }

    public void myCardButtonClicked(ActionEvent event) throws IOException {
        switchScene.switchToMyCardScene(event, username, user);
    }

    public void myTicketsButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToMyTicketsScene(event, username, user);
    }
}
