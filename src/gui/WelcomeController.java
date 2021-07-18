package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class WelcomeController {

    public void loginButtonClicked(ActionEvent event) throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void regButtonClicked(ActionEvent event)throws IOException
    {
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void exitButtonClicked(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.close();
    }
}
