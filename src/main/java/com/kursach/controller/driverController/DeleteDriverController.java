package com.kursach.controller.driverController;

import com.kursach.model.Driver;
import com.kursach.service.DriverService;
import com.kursach.service.impl.DriverServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteDriverController {

    @FXML
    private TextField driverIdField;

    @FXML
    private ListView<String> driverInformationListView;

    @FXML
    private Button findById;

    DriverService driverService = new DriverServiceImpl();


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) driverIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteDriver(ActionEvent event) {
        driverService.deleteDriver(Integer.parseInt(driverIdField.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Водитель успешно удален");
        alert.showAndWait();
    }

    @FXML
    public void findDriverById(ActionEvent event) throws SQLException {
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
        driverInformationListView.setItems(observableList);
    }
}