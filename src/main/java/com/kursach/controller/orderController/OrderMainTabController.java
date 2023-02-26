package com.kursach.controller.orderController;

import com.kursach.interfaceForController.TabGetWindow;
import com.kursach.model.Order;
import com.kursach.service.OrderService;
import com.kursach.service.impl.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderMainTabController extends TabGetWindow {

    private final static String URL_ALL_ORDERS_FXML = "/view/order/orderList.fxml";
    private final static String URL_ADD_ORDER_FXML = "/view/order/addOrder.fxml";
    private final static String URL_UPDATE_ORDER_FXML = "/view/order/updateOrder.fxml";
    private final static String URL_DELETE_ORDER_FXML = "/view/order/deleteOrder.fxml";
    private final static String URL_FULL_INFORMATION_BY_ID_ORDER_FXML = "/view/order/fullInfoById.fxml";

    private final static String ALL_ORDERS_TITLE = "Список всех перевозок";
    private final static String ADD_ORDER_TITLE = "Добавить новую перевозку";
    private final static String UPDATE_ORDER_TITLE = "Изменить данные о перевозке";
    private final static String DELETE_ORDER_TITLE = "Удалить перевозку";
    private final static String FULL_INFORMATION_BY_ID_TITLE = "Удалить перевозку";

    private final OrderService orderService = new OrderServiceImpl();

    @FXML
    public TextField orderIdField;

    @FXML
    public ListView<String> orderInfoListView;

    @FXML
    public void getAllOrderPage(ActionEvent event) {
        getWindow(URL_ALL_ORDERS_FXML, ALL_ORDERS_TITLE);
    }
    @FXML
    public void getAddOrderPage(ActionEvent event) {
        getWindow(URL_ADD_ORDER_FXML, ADD_ORDER_TITLE);
    }
    @FXML
    public void getUpdateOrderPage(ActionEvent event) {
        getWindow(URL_UPDATE_ORDER_FXML, UPDATE_ORDER_TITLE);
    }
    @FXML
    public void getDeleteOrderPage(ActionEvent event) {
        getWindow(URL_DELETE_ORDER_FXML, DELETE_ORDER_TITLE);
    }

    @FXML
    public void getByIDPage(ActionEvent event) {
        getWindow(URL_FULL_INFORMATION_BY_ID_ORDER_FXML, FULL_INFORMATION_BY_ID_TITLE);
    }

    @FXML
    public void findById(ActionEvent event) {
        getOrderInfo();
    }

    private void getOrderInfo(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        Order order = orderService.getById(Integer.parseInt(orderIdField.getText()));
        observableList.add("Начало маршрута: " + order.getStartPoint());
        observableList.add("Конец маршрута: " +order.getEndPoint());
        observableList.add("Цена перевозки: " +order.getCoast().toString());
        observableList.add("Перевозимый объем топлива: " +order.getFuelVolume().toString());
        orderInfoListView.setItems(observableList);
    }


}
