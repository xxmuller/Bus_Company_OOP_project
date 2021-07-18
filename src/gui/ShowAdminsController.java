package gui;

import Admins.Admin;
import Admins.CEO;
import Admins.Secretary;
import Data.UserData;
import Users.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

public class ShowAdminsController {
    @FXML
    private TableView<UserInfo> usersTable;
    @FXML
    private TableColumn<?, ?> firstNameColumn;
    @FXML
    private TableColumn<?, ?>  lastNameColumn;
    @FXML
    private TableColumn<?, ?>  dateOfBirthColumn;
    @FXML
    private TableColumn<?, ?>  categoryColumn;
    @FXML
    private TableColumn<?, ?>  usernameColumn;
    @FXML
    private TableColumn<?, ?>  passwordColumn;
    @FXML
    private Button updateButton, deleteButton, chngInfoButton;
    @FXML
    private MenuButton menuButton;
    @FXML
    private TextField newValue_tf;
    private SwitchScene switchScene = new SwitchScene();
    private Admin admin;
    private ObservableList<UserInfo> userData;
    private UserData data = new UserData();
    private String infoToChange;

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    //nastavi tabulku
    public void setTable(){
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public void initialize() throws SQLException {
        userData = FXCollections.observableArrayList();
        this.setTable();
        this.loadDataFromDatabase();
        //mazat adminov z db a menit ich info moze len editor
        if(admin instanceof CEO || admin instanceof Secretary){
            deleteButton.setVisible(false);
            chngInfoButton.setVisible(false);
        }
        updateButton.setVisible(false);
        newValue_tf.setVisible(false);
        menuButton.setVisible(false);
    }

    //vycistenie tabulky
    public void clearTable(){
        usersTable.getItems().clear();
    }

    public void deleteAdminButtonClicked() {
        try{
            //pracuje iba s tym adminom, ktory je oznaceny v tabulke
            UserInfo info = usersTable.getSelectionModel().getSelectedItem();
            //mazanie uzivatela z db
            data.deleteUser(info.getUserName(), "admins");
            this.clearTable();
            this.loadDataFromDatabase();
        }catch (Exception e){
            System.out.println("No user selected.");
        }
    }

    public void changeInfoButtonClicked(){
        updateButton.setVisible(true);
        newValue_tf.setVisible(true);
        menuButton.setVisible(true);
    }

    public void fNameOpt(){
        menuButton.setText("First Name");
        this.infoToChange = "fName";
    }

    public void lNameOpt(){
        menuButton.setText("Last Name");
        this.infoToChange = "lName";
    }

    public void dateofBirthOpt(){
        menuButton.setText("Date of birth");
        this.infoToChange = "dateOfBirth";
    }

    public void categoryOpt(){
        menuButton.setText("Category");
        this.infoToChange = "category";
    }

    public void usernameOpt(){
        menuButton.setText("Username");
        this.infoToChange = "username";
    }

    public void passwordOpt(){
        menuButton.setText("Password");
        this.infoToChange = "password";
    }

    public void updateButtonClicked(){
        //ak admin vybral info, ktore chce zmenit a napisal aj novu honotu daneho infa
        if(infoToChange != null && newValue_tf.getText() != null){
            try{
                //pracuje iba s adminom, ktory je oznaceny v tabulke
                UserInfo info = usersTable.getSelectionModel().getSelectedItem();
                //aktualizovanie infa
                data.updateUserInfo(info.getUserName(), infoToChange, newValue_tf.getText(), "admins");
                //reset tabulky
                this.clearTable();
                this.loadDataFromDatabase();
            }catch(Exception e){
                System.out.println("You must select user from table in order to change user's info.");
            }
        }else
            System.out.println("First select what to change and type new value.");
    }

    //z databazy nahodi informacie o vsetkych adminoch do listu
    private void loadDataFromDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM admins");
        ResultSet rslt = statement.getResultSet();

        while(rslt.next()){
            userData.add(new UserInfo(rslt.getString("fName"), rslt.getString("lName"), rslt.getString("dateOfBirth"),
                    rslt.getString("category"), rslt.getString("username"),
                    rslt.getString("password"), ""));
        }

        //vyplnenie tabulky
        usersTable.setItems(userData);
    }

    public void showUsersButtonClicked(ActionEvent event) throws IOException, SQLException {
        switchScene.switchToShowUsers(event, admin);
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