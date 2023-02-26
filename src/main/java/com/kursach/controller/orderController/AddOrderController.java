package com.kursach.controller.orderController;

import com.kursach.model.Driver;
import com.kursach.model.Fuel;
import com.kursach.model.Order;
import com.kursach.service.DriverService;
import com.kursach.service.FuelService;
import com.kursach.service.OrderService;
import com.kursach.service.impl.DriverServiceImpl;
import com.kursach.service.impl.FuelServiceImpl;
import com.kursach.service.impl.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddOrderController implements Initializable {

    @FXML
    private Button addOrder;
    @FXML
    private Text autoBox;
    @FXML
    private Button cancel;
    @FXML
    private TextField coastField;
    @FXML
    private TextField distanceField;
    @FXML
    private ComboBox<Driver> driverBox;
    @FXML
    private ComboBox<Fuel> fuelTypeBox;
    @FXML
    private TextField fuelVolumeField;
    @FXML
    private TextField startPointField;
    @FXML
    private TextField endPointField;

    private final OrderService orderService = new OrderServiceImpl();
    private final DriverService driverService = new DriverServiceImpl();
    private final FuelService fuelService = new FuelServiceImpl();

    @FXML
    void addOrder(ActionEvent event) {
        orderService.addOrder(getOrderFromView());
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) addOrder.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showDataBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDataBox() throws SQLException {
        getDriverData();
        getFuelData();
    }

    private void getDriverData() throws SQLException {
        ObservableList<Driver> observableList = FXCollections.observableArrayList(driverService.getAll());
        driverBox.setItems(observableList);
    }

    private void getFuelData() throws SQLException {
        ObservableList<Fuel> observableList = FXCollections.observableArrayList(fuelService.getAll());
        fuelTypeBox.setItems(observableList);
    }

    private Order getOrderFromView(){
        Order order = new Order();
        order.setStartPoint(startPointField.getText());
        order.setEndPoint(endPointField.getText());
        order.setCoast(Integer.parseInt(coastField.getText()));
        order.setDistance(Double.parseDouble(distanceField.getText()));
        order.setDriver(driverBox.getSelectionModel().getSelectedItem());
        order.setAuto(driverBox.getSelectionModel().getSelectedItem().getAuto());
        order.setFuel(fuelTypeBox.getSelectionModel().getSelectedItem());
        order.setFuelVolume(Integer.parseInt(fuelVolumeField.getText()));
        return order;
    }
}
