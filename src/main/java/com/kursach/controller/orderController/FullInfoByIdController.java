package com.kursach.controller.orderController;

import com.kursach.model.Order;
import com.kursach.service.OrderService;
import com.kursach.service.impl.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FullInfoByIdController {

    private final OrderService orderService = new OrderServiceImpl();


    @FXML
    private Button cancel;

    @FXML
    private TextField orderIdField;

    @FXML
    private ListView<String> orderInfoListView;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) orderIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void findById(ActionEvent event) {
        getOrderInfo();
    }

    private void getOrderInfo(){
        Order order = orderService.getById(Integer.parseInt(orderIdField.getText()));
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("ID перевозки: " + order.getId());
        observableList.add("Стартовая точка маршрута: " + order.getStartPoint());
        observableList.add("Конечная точка маршрута: " + order.getEndPoint());
        observableList.add("Стоимость перевозки: " + order.getCoast());
        observableList.add("Общее расстояние: " + order.getDistance());
        observableList.add("Тип перевезенного топлива: " + order.getFuel());
        observableList.add("Количество перевезнного топлива: " + order.getFuelVolume());
        observableList.add("Автомобиль перевозчик: " + order.getAuto());
        observableList.add("Водитель автомобиля: " + order.getDriver());
        orderInfoListView.setItems(observableList);
    }
}
