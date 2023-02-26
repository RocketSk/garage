package com.kursach.controller.driverController;

import com.kursach.model.Driver;
import com.kursach.service.impl.DriverServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DriverListController implements Initializable {

    @FXML
    private TableColumn<Driver, String> driverAuto;

    @FXML
    private TableColumn<Driver, String> driverFathersNameCol;

    @FXML
    private TableColumn<Driver, String> driverFirstNameCol;

    @FXML
    private TableColumn<Driver, Integer> driverIdCol;

    @FXML
    private TableColumn<Driver, String> driverLastNameCol;

    @FXML
    private TableView<Driver> driverTableView;

    DriverServiceImpl service = new DriverServiceImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showDriver();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDriver() throws SQLException {
        ObservableList<Driver> observableList = FXCollections.observableArrayList(service.getAll());
        driverIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        driverLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        driverFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        driverFathersNameCol.setCellValueFactory(new PropertyValueFactory<>("fathersName"));
        driverAuto.setCellValueFactory(new PropertyValueFactory<>("autoNum"));
        //RocketSk
        driverTableView.setItems(observableList);
    }
}
