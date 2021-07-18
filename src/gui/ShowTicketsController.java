package gui;

import Admins.Admin;
import Data.Tickets;
import Users.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

public class ShowTicketsController {
    @FXML
    private TableView<Tickets> ticketsTable;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> ticketTypeColumn;
    @FXML
    private TableColumn<?, ?> discountColumn;
    private SwitchScene switchScene = new SwitchScene();
    private Admin admin;
    private ObservableList<Tickets> tickets;

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public void setTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    public void initialize() throws SQLException {
        tickets = FXCollections.observableArrayList();
        this.setTable();
        this.loadDataFromDatabase();
    }

    //z databazy nacita informacie o vsetkych listkoch
    private void loadDataFromDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM tickets");
        ResultSet rslt = statement.getResultSet();

        while(rslt.next()){
            tickets.add(new Tickets(rslt.getString("name"), rslt.getString("ticketType"), rslt.getString("discount")));
        }

        //vyplnenie tabulky
        ticketsTable.setItems(tickets);
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

    public void logoutButtonClicked(ActionEvent event) throws IOException
    {
        switchScene.logOut(event);
    }
}
