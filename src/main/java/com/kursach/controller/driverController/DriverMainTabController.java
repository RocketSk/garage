package com.kursach.controller.driverController;

import com.kursach.interfaceForController.TabGetWindow;
import com.kursach.model.Driver;
import com.kursach.service.DriverService;
import com.kursach.service.impl.DriverServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DriverMainTabController extends TabGetWindow implements Initializable {

    private final static String URL_ALL_DRIVERS_FXML = "/view/driver/driverList.fxml";
    private final static String URL_ADD_DRIVER_FXML = "/view/driver/addDriver.fxml";
    private final static String URL_UPDATE_DRIVER_FXML = "/view/driver/updateDriver.fxml";
    private final static String URL_DELETE_DRIVER_FXML = "/view/driver/deleteDriver.fxml";

    private final static String ALL_DRIVERS_TITLE = "Список всех водителей";
    private final static String ADD_DRIVER_TITLE = "Добавить нового водителя";
    private final static String UPDATE_DRIVER_TITLE = "Изменить данные водителя";
    private final static String DELETE_DRIVER_TITLE = "Удалить водителя";

    @FXML
    public ListView<String> driverInfoListView;
    @FXML
    public TextField driverIdField;

    DriverService driverService = new DriverServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void getAllDriversPage(ActionEvent event) {
        getWindow(URL_ALL_DRIVERS_FXML, ALL_DRIVERS_TITLE);
    }

    @FXML
    public void getAddDriverPage(ActionEvent event) {
        getWindow(URL_ADD_DRIVER_FXML, ADD_DRIVER_TITLE);
    }

    @FXML
    public void getUpdateDriverPage(ActionEvent event) {
        getWindow(URL_UPDATE_DRIVER_FXML, UPDATE_DRIVER_TITLE);
    }

    @FXML
    public void getDeleteDriverPage(ActionEvent event) {
        getWindow(URL_DELETE_DRIVER_FXML, DELETE_DRIVER_TITLE);
    }

    @FXML
    public void findById(ActionEvent event) throws SQLException {
        Driver driver = driverService.getByID(Integer.parseInt(driverIdField.getText()));
        showDriverInfo(driver);
    }

    private void showDriverInfo(Driver driver){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("ID водителя : " + driver.getId());
        observableList.add("Имя водителя : " + driver.getFirstName());
        observableList.add("Фамилия водителя : " + driver.getLastName());
        observableList.add("Отчество водителя : " + driver.getFathersName());
        observableList.add("Бренд авто водителя : " + driver.getAuto().getBrand());
        observableList.add("Модель авто водителя : " + driver.getAuto().getModel());
        observableList.add("Номер авто водителя : " + driver.getAuto().getNum());
        driverInfoListView.setItems(observableList);
    }
}
