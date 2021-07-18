package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    private Button enterButton = new Button("ENTER");
    private Button closeButton = new Button("CLOSE");
    private AnchorPane anchorPane = new AnchorPane();
    private AnchorPane labelAnchorPane = new AnchorPane();
    private Label label = new Label("Enter BestBus App?");

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("BestBus");

        enterButton.setLayoutX(530);
        enterButton.setLayoutY(300);
        enterButton.setMinHeight(70);
        enterButton.setMinWidth(170);
        enterButton.setFont(new Font(20));
        enterButton.setStyle("-fx-text-fill: white; -fx-background-color: #e91e63");

        closeButton.setLayoutX(550);
        closeButton.setLayoutY(450);
        closeButton.setMinHeight(60);
        closeButton.setMinWidth(130);
        closeButton.setFont(new Font(20));
        closeButton.setStyle("-fx-text-fill: white; -fx-background-color: #e91e63");

        label.setLayoutX(450);
        label.setLayoutY(60);
        label.setFont(new Font(40));
        label.setStyle("-fx-text-fill: white");

        labelAnchorPane.setMinHeight(170);
        labelAnchorPane.setMinWidth(1200);
        labelAnchorPane.setStyle("-fx-background-color: #6200EE");

        anchorPane.getChildren().add(labelAnchorPane);
        anchorPane.getChildren().add(enterButton);
        anchorPane.getChildren().add(closeButton);
        anchorPane.getChildren().add(label);
        anchorPane.setStyle("-fx-background-color: white");

        enterButton.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
                primaryStage.setScene(new Scene(root, 1200, 750));
                primaryStage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        closeButton.setOnAction(e -> {
            primaryStage.close();
        });

        primaryStage.setScene(new Scene(anchorPane, 1200, 750));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

}
