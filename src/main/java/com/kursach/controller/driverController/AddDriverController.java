package com.kursach.controller.driverController;

import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.service.AutoService;
import com.kursach.service.DriverService;
import com.kursach.service.impl.AutoServiceImpl;
import com.kursach.service.impl.DriverServiceImpl;
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

public class AddDriverController implements Initializable {

    @FXML
    private AnchorPane rootPane;


    @FXML
    private ComboBox<Auto> driverAutoNum;

    @FXML
    private TextField driverFathersName;

    @FXML
    private TextField driverFirstName;

    @FXML
    private TextField driverSecondName;

    @FXML
    private Button saveDriverButton;

    @FXML
    private Button cancelSaveDriverButton;

    private final DriverService driverService = new DriverServiceImpl();
    private final AutoService autoService = new AutoServiceImpl();

    @FXML
    void cancelSaveDriver(ActionEvent event){
        Stage stage = (Stage) cancelSaveDriverButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveDriver(ActionEvent event) throws SQLException {
        Driver driver = new Driver();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (isValidated(alert)) {
            Auto auto = autoService.getById(
                    driverAutoNum.
                            getSelectionModel().
                            getSelectedItem().
                            getId());
            driver.setFirstName(driverFirstName.getText());
            driver.setLastName(driverSecondName.getText());
            driver.setFathersName(driverFathersName.getText());
            driver.setAutoNum(auto.getNum());
            System.out.println("Я добавляю авто");
            driver.setAuto(auto);
            auto.getDrivers().add(driver);
            System.out.println("Добавил драйвера");
            driverService.addDriver(driver);
            alert.setHeaderText("Успешно");
            alert.setContentText("Водитель добавлен");
            alert.showAndWait();
        }
    }

    public boolean isValidated(Alert alert) {
        alert.setAlertType(Alert.AlertType.WARNING);
        if (driverFirstName.getText().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните поле с именем водителя");
            alert.showAndWait();
            return false;
        } else if (driverSecondName.getText().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните поле с фамилией водителя");
            alert.showAndWait();
            return false;
        } else if (driverFathersName.getText().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните поле с отчеством водителя");
            alert.showAndWait();
            return false;
        } else if (driverAutoNum.getSelectionModel().isEmpty()) {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, выберете автомобиль для водителя");
            alert.showAndWait();
            return false;
        }
        alert.setAlertType(Alert.AlertType.INFORMATION);
        return true;
    }

    public void getAutoTitle() throws SQLException {
        ObservableList<Auto> listAuto = FXCollections.observableArrayList(autoService.getAll());
        driverAutoNum.setItems(listAuto);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getAutoTitle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
