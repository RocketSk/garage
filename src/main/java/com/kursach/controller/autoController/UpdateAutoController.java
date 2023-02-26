package com.kursach.controller.autoController;

import com.kursach.model.Auto;
import com.kursach.model.Fuel;
import com.kursach.service.AutoService;
import com.kursach.service.FuelService;
import com.kursach.service.impl.AutoServiceImpl;
import com.kursach.service.impl.FuelServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateAutoController {

    private final AutoService autoService = new AutoServiceImpl();

    private final FuelService fuelService = new FuelServiceImpl();

    @FXML
    private TextField autoBrandField;

    @FXML
    private ComboBox<Fuel> autoFuelComboBox;

    @FXML
    private TextField autoModelField;

    @FXML
    private TextField autoNumField;

    @FXML
    private Button findByIDButton;

    @FXML
    private TextField idForUpdateAutoField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) findByIDButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void findByID(ActionEvent event) throws SQLException {
        Auto auto = autoService.getById(Integer.parseInt(idForUpdateAutoField.getText()));
        showAutoInfo(auto);
    }

    @FXML
    void updateAuto(ActionEvent event) throws SQLException {
        updateAuto();
    }

    private void showAutoInfo(Auto auto) throws SQLException {
        autoNumField.setText(auto.getNum());
        autoBrandField.setText(auto.getBrand());
        autoModelField.setText(auto.getModel());
        autoFuelComboBox.getItems().setAll(fuelService.getAll());
        autoFuelComboBox.setValue(auto.getFuel());
    }

    private void updateAuto() throws SQLException {
        Auto auto = autoService.getById(Integer.parseInt(idForUpdateAutoField.getText()));
        auto.setNum(autoNumField.getText());
        auto.setBrand(autoBrandField.getText());
        auto.setModel(autoModelField.getText());
        auto.setFuel(autoFuelComboBox.getSelectionModel().getSelectedItem());
        autoService.updateAuto(auto);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Автомобиль успешно обновлен");
        alert.showAndWait();
    }

}
