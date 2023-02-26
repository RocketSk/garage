package com.kursach.controller.fuelController;

import com.kursach.interfaceForController.TabGetWindow;
import com.kursach.model.Fuel;
import com.kursach.service.FuelService;
import com.kursach.service.impl.FuelServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FuelMainTabController extends TabGetWindow implements Initializable {

    private final static String URL_ADD_FUEL_FXML = "/view/fuel/addFuel.fxml";
    private final static String URL_CHANGE_FUEL_BALANCE_FXML = "/view/fuel/changeFuelBalance.fxml";
    private final static String URL_DELETE_FUEL_FXML = "/view/fuel/deleteFuel.fxml";
    private final static String ADD_FUEL_TITLE = "Добавить новый вид топлива";
    private final static String CHANGE_FUEL_BALANCE_TITLE = "Изменить количество топлива";
    private final static String DELETE_FUEL_TITLE = "Добавить удалить вид топлива";

    @FXML
    public Button refreshFuelTable;
    @FXML
    public TableView<Fuel> fuelTableView;
    @FXML
    public TableColumn<Fuel, String> nameFuelColumn;
    @FXML
    public TableColumn<Fuel, Integer> volumeFuelColumn;
    @FXML
    public Button addFuel;
    @FXML
    public Button deleteFuel;

    private final FuelService fuelService = new FuelServiceImpl();

    @FXML
    void addNewFuelType(ActionEvent event){
        getWindow(URL_ADD_FUEL_FXML, ADD_FUEL_TITLE);
    }

    @FXML
    void deleteFuel(ActionEvent event) {
        getWindow(URL_DELETE_FUEL_FXML, DELETE_FUEL_TITLE);
    }

    @FXML
    public void changeFuelBalance(ActionEvent event) {
        getWindow(URL_CHANGE_FUEL_BALANCE_FXML, CHANGE_FUEL_BALANCE_TITLE);
    }

    @FXML
    void refreshFuelTable(ActionEvent event) throws SQLException {
        showFuelBalance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showFuelBalance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showFuelBalance() throws SQLException {
        ObservableList<Fuel> observableList = FXCollections.observableArrayList(fuelService.getAll());
        nameFuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        volumeFuelColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        fuelTableView.setItems(observableList);
    }

}
