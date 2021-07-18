package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import Data.*;

public class SignUpController {
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_dateOfBirth;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_idcard;
    @FXML
    private MenuButton chooseCategoryMenu;
    private UserData data = new UserData();
    private static SwitchScene switchScene = new SwitchScene();

    private String category;

    //vyber kategorie pri registracii
    public void studentOptClicked(){
        chooseCategoryMenu.setText("Student");
        this.category = "student";
    }

    public void pensionOptClicked(){
        chooseCategoryMenu.setText("Pensioner");
        this.category = "pension";
    }

    public void ztpOptClicked(){
        chooseCategoryMenu.setText("ZTP");
        this.category = "ztp";
    }

    //po uspesnej registracii vybehne okno s potvrdenim
    public static void SignedInPopUp(){
        Stage window = new Stage();
        Button logInButton = new Button("Log In");
        Label label = new Label();

        window.setMinWidth(400);
        window.setMinHeight(250);
        window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox();
        layout.getChildren().addAll(logInButton ,label);
        layout.setAlignment(Pos.CENTER);

        label.setText("User Registered");

        logInButton.setOnAction(e -> {
            window.close();
        });

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void regButtonClicked(ActionEvent event) throws SQLException{
        String fname = tf_firstName.getText();
        String lname = tf_lastName.getText();
        String email = tf_email.getText();
        String dateOfBirth = tf_dateOfBirth.getText();
        String username = tf_username.getText();
        String password = tf_password.getText();
        String idCard = tf_idcard.getText();

        //ak uzivatel vyplnil vsetky povinne polia prida ho do databazy a vybehne okno s potvrdenim o registracii
        if(fname != null && lname != null && email != null && dateOfBirth != null && username != null
            && password != null){
            data.addUser(fname, lname, email, dateOfBirth, category, username, password, idCard);
            System.out.println("User " + username + " registered.");

            SignedInPopUp();
        }

        try {
            switchScene.logIn(event);
        } catch (IOException e1) {
            e1.printStackTrace();
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