package com.kursach.controller.autoController;

import com.kursach.model.Auto;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddAutoController implements Initializable {

    private final FuelService fuelService = new FuelServiceImpl();

    @FXML
    public ComboBox<Fuel> fuelTypeComboBox;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField autoNum;

    @FXML
    private TextField autoBrand;

    @FXML
    private TextField autoModel;

    @FXML
    private Button saveAutoButton;

    @FXML
    private Button cancelSaveAutoButton;

    private final AutoService service = new AutoServiceImpl();

    @FXML
    void cancelSaveAuto(ActionEvent event){
        Stage stage = (Stage) cancelSaveAutoButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveAuto(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(isValidated(alert)){
            Auto auto = new Auto();
            auto.setNum(autoNum.getText());
            auto.setBrand(autoBrand.getText());
            auto.setModel(autoModel.getText());
            auto.setFuel(fuelTypeComboBox.getSelectionModel().getSelectedItem());
            service.addAuto(auto);
            alert.setHeaderText("Успешно");
            alert.setContentText("Автомобиль добавлен");
            alert.showAndWait();
        }
    }

    public void getFuelTitle() throws SQLException {
        ObservableList<Fuel> fuelList = FXCollections.observableArrayList(fuelService.getAll());
        fuelTypeComboBox.setItems(fuelList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getFuelTitle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidated(Alert alert) {
        alert.setAlertType(Alert.AlertType.WARNING);
        if (autoNum.getText().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните поле с номером автомобиля");
            alert.showAndWait();
            return false;
        } else if (autoBrand.getText().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните поле с брендом автомобиля");
            alert.showAndWait();
            return false;
        } else if (autoModel.getText().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните поле с моделью");
            alert.showAndWait();
            return false;
        } else if (fuelTypeComboBox.getSelectionModel().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, выберете тип топлива для автомобиля");
            alert.showAndWait();
            return false;
        }
        alert.setAlertType(Alert.AlertType.INFORMATION);
        return true;
    }

}
