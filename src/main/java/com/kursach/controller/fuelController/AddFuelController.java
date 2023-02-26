package com.kursach.controller.fuelController;

import com.kursach.model.Fuel;
import com.kursach.service.FuelService;
import com.kursach.service.impl.FuelServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFuelController implements Initializable {

    FuelService fuelService = new FuelServiceImpl();

    @FXML
    private Button cancelSaveFuelButton;

    @FXML
    private TextField fuelTypeName;

    @FXML
    private Button saveFuelButton;

    @FXML
    void cancelSaveFuel(ActionEvent event) {
        Stage stage = (Stage) cancelSaveFuelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveFuel(ActionEvent event) {
        Fuel fuel = new Fuel();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(isValidated(alert)){
            fuel.setFuelType(fuelTypeName.getText());
            fuel.setCount(0);
            fuelService.addFuel(fuel);
            alert.setHeaderText(null);
            alert.setContentText("Новый вид топлива добавлен");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean isValidated(Alert alert) {
        alert = new Alert(Alert.AlertType.WARNING);
        if (fuelTypeName.getText().isEmpty()) {
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, заполните поле с названием для топлива");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
