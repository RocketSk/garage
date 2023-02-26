package com.kursach.controller.fuelController;

import com.kursach.model.Fuel;
import com.kursach.service.FuelService;
import com.kursach.service.impl.FuelServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeFuelBalanceController implements Initializable {

    private final FuelService fuelService = new FuelServiceImpl();

    @FXML
    private TextField fuelCountToPlusField;

    @FXML
    private TextField fuelCountToSubField;

    @FXML
    private ComboBox<Fuel> fuelTypeBox;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) fuelCountToPlusField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void plusFuel(ActionEvent event) {
        System.out.println("я тут");
        if(Integer.parseInt(fuelCountToPlusField.getText()) >=0){
            Fuel fuel = fuelTypeBox.getSelectionModel().getSelectedItem();
            fuel.setCount(fuel.getCount() + Integer.parseInt(fuelCountToPlusField.getText()));
            fuelService.updateFuel(fuel);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Успешно");
            alert.setContentText("Топливо успешно увеличино");
            alert.showAndWait();
        } else if (Integer.parseInt(fuelCountToPlusField.getText()) < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Количество не может быть меньше нуля");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Введите количество которое нужно прибавить");
            alert.showAndWait();
        }
    }

    @FXML
    void subFuel(ActionEvent event) {
        if(Integer.parseInt(fuelCountToSubField.getText()) >=0){
            Fuel fuel = fuelTypeBox.getSelectionModel().getSelectedItem();
            Integer count = fuel.getCount() - Integer.parseInt(fuelCountToSubField.getText());
            if (count > 0){
                fuel.setCount(count);
                fuelService.updateFuel(fuel);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Успешно");
                alert.setContentText("Топливо успешно увеличино");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Ошибка");
                alert.setContentText("На складе отсутствует нужное количество топлива");
                alert.showAndWait();
            }
        } else if (Integer.parseInt(fuelCountToSubField.getText()) < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Количество не может быть меньше нуля");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Введите количество которое нужно отнять");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showFuel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showFuel() throws SQLException {
        ObservableList<Fuel> observableList = FXCollections.observableArrayList();
        observableList.addAll(fuelService.getAll());
        fuelTypeBox.setItems(observableList);
    }
}
