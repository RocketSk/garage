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

public class DeleteOrderController {

    private final OrderService orderService = new OrderServiceImpl();

    @FXML
    private Button findById;

    @FXML
    private ListView<String> orderInformationListView;

    @FXML
    private TextField orderIdField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) findById.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteOrder(ActionEvent event) {
        Order order = orderService.getById(Integer.parseInt(orderIdField.getText()));
        orderService.deleteOrder(order);
    }

    @FXML
    void findOrderById(ActionEvent event) {
        Order order = orderService.getById(Integer.parseInt(orderIdField.getText()));
        showOrderInfo(order);
    }

    private void showOrderInfo(Order order) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add(order.getId().toString());
        observableList.add(order.getStartPoint());
        observableList.add(order.getEndPoint());
        observableList.add(order.getDistance().toString());
        observableList.add(order.getCoast().toString());
        observableList.add(order.getFuel().toString());
        observableList.add(order.getFuelVolume().toString());
        observableList.add(order.getAuto().getNum());
        observableList.add(order.getDriver().getFirstName() + " " +
                order.getDriver().getLastName() + " " +
                order.getDriver().getFathersName());
        orderInformationListView.setItems(observableList);
    }

}
