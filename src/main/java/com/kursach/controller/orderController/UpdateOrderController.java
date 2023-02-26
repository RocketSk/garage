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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateOrderController {

    private final OrderService orderService = new OrderServiceImpl();
    private final FuelService fuelService = new FuelServiceImpl();
    private final DriverService driverService = new DriverServiceImpl();

    @FXML
    private TextField coastField;

    @FXML
    private TextField distanceField;

    @FXML
    private ComboBox<Driver> driverComboBox;

    @FXML
    private TextField endPointField;

    @FXML
    private ComboBox<Fuel> fuelTypeComboBox;

    @FXML
    private TextField fuelVolumeField;

    @FXML
    private TextField idForUpdateOrderField;

    @FXML
    private TextField startPontField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) startPontField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void findByID(ActionEvent event) throws SQLException {
        getInfo();
    }

    @FXML
    void updateOrder(ActionEvent event) {
        updateOrder();
    }

    private void getInfo() throws SQLException {
        ObservableList<Fuel> fuelObservableList=FXCollections.observableArrayList(fuelService.getAll());
        ObservableList<Driver> driverObservableList=FXCollections.observableArrayList(driverService.getAll());
        Order order = orderService.getById(Integer.parseInt(idForUpdateOrderField.getText()));
        startPontField.setText(order.getStartPoint());
        endPointField.setText(order.getEndPoint());
        distanceField.setText(order.getDistance().toString());
        fuelVolumeField.setText(order.getFuelVolume().toString());
        coastField.setText(order.getCoast().toString());
        fuelTypeComboBox.setItems(fuelObservableList);
        fuelTypeComboBox.setValue(order.getFuel());
        driverComboBox.setItems(driverObservableList);
        driverComboBox.setValue(order.getDriver());
    }

    private void updateOrder(){
        Order order = orderService.getById(Integer.parseInt(idForUpdateOrderField.getText()));
        order.setDistance(Double.parseDouble(distanceField.getText()));
        order.setDriver(driverComboBox.getSelectionModel().getSelectedItem());
        order.setAuto(driverComboBox.getSelectionModel().getSelectedItem().getAuto());
        order.setFuel(fuelTypeComboBox.getSelectionModel().getSelectedItem());
        order.setCoast(Integer.parseInt(coastField.getText()));
        order.setStartPoint(startPontField.getText());
        order.setEndPoint(startPontField.getText());
        order.setFuelVolume(Integer.parseInt(fuelVolumeField.getText()));
        orderService.updateOrder(order);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Перевозка успешно обновлена");
        alert.showAndWait();
    }

}
