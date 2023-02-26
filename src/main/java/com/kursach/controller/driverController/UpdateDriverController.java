package com.kursach.controller.driverController;

import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.service.AutoService;
import com.kursach.service.DriverService;
import com.kursach.service.impl.AutoServiceImpl;
import com.kursach.service.impl.DriverServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateDriverController {

    @FXML
    private ComboBox<Auto> driverAutoComboBox;

    @FXML
    private TextField driverFathersNameField;

    @FXML
    private TextField driverFirstNameField;

    @FXML
    private TextField driverSecondNameField;

    @FXML
    private TextField idForUpdateDriverField;

    @FXML
    private Button findByIDButton;


    private final DriverService driverService = new DriverServiceImpl();
    private final AutoService autoService = new AutoServiceImpl();

    @FXML
    void findByID(ActionEvent event) throws SQLException {
        Driver driver = driverService.getByID(Integer.parseInt(idForUpdateDriverField.getText()));
        showDriverInfo(driver);
    }

    @FXML
    void updateDriver(ActionEvent event) throws SQLException {
        updateDriver();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) findByIDButton.getScene().getWindow();
        stage.close();
    }

    private void showDriverInfo(Driver driver) throws SQLException {
        driverFirstNameField.setText(driver.getFirstName());
        driverSecondNameField.setText(driver.getFathersName());
        driverFathersNameField.setText(driver.getFathersName());
        driverAutoComboBox.getItems().addAll(autoService.getAll());
        driverAutoComboBox.setValue(driver.getAuto());
    }

    private void updateDriver() throws SQLException {
        Driver driver = driverService.getByID(Integer.parseInt(idForUpdateDriverField.getText()));
        driver.setFirstName(driverFirstNameField.getText());
        driver.setLastName(driverSecondNameField.getText());
        driver.setFathersName(driverFathersNameField.getText());
        driver.setAuto(driverAutoComboBox.getSelectionModel().getSelectedItem());
        driver.setAutoNum(driver.getAuto().getNum());
        driverService.updateDriver(driver);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Водитель успешно обновлен");
        alert.showAndWait();
    }


}
