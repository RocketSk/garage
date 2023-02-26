package com.kursach.controller.fuelController;

import com.kursach.model.Fuel;
import com.kursach.service.AutoService;
import com.kursach.service.FuelService;
import com.kursach.service.impl.AutoServiceImpl;
import com.kursach.service.impl.FuelServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteFuelController implements Initializable {

    @FXML
    private ComboBox<Fuel> fuelTypeBox;

    private final FuelService fuelService = new FuelServiceImpl();
    private final AutoService autoService = new AutoServiceImpl();

    @FXML
    void deleteFuelType(ActionEvent event) throws SQLException {
        Fuel fuel = fuelTypeBox.getSelectionModel().getSelectedItem();
        if (autoService.getAllByFuel(fuel).isEmpty()){
            fuelService.deleteFuel(fuel);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Успешно");
            alert.setContentText("Тип топлива успешно удален");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText(
                    "К топливу, до сих пор привязаны автомобили, \n" +
                            "вы не можете удалить топливо\n +" +
                            "пока за ним закреплены автомобили");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showFuel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFuel() throws SQLException {
        ObservableList<Fuel> observableList = FXCollections.observableArrayList();
        observableList.addAll(fuelService.getAll());
        fuelTypeBox.setItems(observableList);
    }
}
