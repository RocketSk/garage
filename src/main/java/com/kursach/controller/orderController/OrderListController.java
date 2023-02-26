package com.kursach.controller.orderController;

import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.model.Fuel;
import com.kursach.model.Order;
import com.kursach.service.OrderService;
import com.kursach.service.impl.OrderServiceImpl;
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

public class OrderListController implements Initializable {

    private final OrderService orderService = new OrderServiceImpl();

    @FXML
    private TableColumn<Order, Auto> orderAutoNumColumn;

    @FXML
    private TableColumn<Order, Integer> orderCoastColumn;

    @FXML
    private TableColumn<Order, Double> orderDistanceColumn;

    @FXML
    private TableColumn<Order, Driver> orderDriverColumn;

    @FXML
    private TableColumn<Order, String> orderEndPointColumn;

    @FXML
    private TableColumn<Order, Fuel> orderFuelColumn;

    @FXML
    private TableColumn<Order, Integer> orderFuelVolumeColumn;

    @FXML
    private TableColumn<Order, Integer> orderIdColumn;

    @FXML
    private TableView<Order> orderInfoTableView;

    @FXML
    private TableColumn<Order, String> orderStartPointColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showOrderInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showOrderInfo() throws SQLException {
        ObservableList<Order> observableList = FXCollections.observableArrayList(orderService.getAll());
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderStartPointColumn.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        orderEndPointColumn.setCellValueFactory(new PropertyValueFactory<>("endPoint"));
        orderCoastColumn.setCellValueFactory(new PropertyValueFactory<>("coast"));
        orderDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        orderFuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        orderDriverColumn.setCellValueFactory(new PropertyValueFactory<>("driver"));
        orderAutoNumColumn.setCellValueFactory(new PropertyValueFactory<>("auto"));
        orderFuelVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelVolume"));
        orderInfoTableView.setItems(observableList);
    }
}
