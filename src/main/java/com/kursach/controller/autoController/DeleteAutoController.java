package com.kursach.controller.autoController;

import com.kursach.dao.impl.AutoDAOImpl;
import com.kursach.model.Auto;
import com.kursach.service.AutoService;
import com.kursach.service.DriverService;
import com.kursach.service.impl.AutoServiceImpl;
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

public class DeleteAutoController {

    @FXML
    private TextField autoIdField;

    @FXML
    private ListView<String> autoInformationListView;

    @FXML
    private Button findById;

    private final AutoService autoService = new AutoServiceImpl();
    private final DriverService driverService = new DriverServiceImpl();

    private final AutoDAOImpl autoDAO = new AutoDAOImpl();



    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) autoIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteAuto(ActionEvent event) {
        Auto auto = autoService.getById(Integer.parseInt(autoIdField.getText()));
        if (auto.getDrivers().isEmpty()){
            autoService.deleteAuto(auto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Успешно");
            alert.setContentText("Автомобиль успешно удален");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText(
                    "К автомобилю, до сих пор привязаны водители, \n" +
                    "вы не можете удалить автомобиль\n +" +
                    "пока за ним закреплены водители");
            alert.showAndWait();
        }

    }

    @FXML
    public void findAutoById(ActionEvent event) throws SQLException {
        Auto auto = autoService.getById(Integer.parseInt(autoIdField.getText()));
        showAutoInfo(auto);
    }

    private void showAutoInfo(Auto auto){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("ID автомобиля : " + auto.getId());
        observableList.add("Бренд автомобиля : " + auto.getBrand());
        observableList.add("Модель автомобиля : " + auto.getModel());
        observableList.add("Номер автомобиля : " + auto.getNum());
        if (auto.getDrivers().isEmpty()) {
            System.out.println(auto.getDrivers());
            observableList.add("За автомобилем еще не закреплено ни одного водителя");
        } else {
            observableList.add("Водители закрепленные за автомобилем : ");
            observableList.add("Всего : " + auto.getDrivers().size());
            for (int i = 0; i < auto.getDrivers().size(); i++) {
                observableList.add("Номер " + (i + 1));
                observableList.add("    " + "ID водителя: " + auto.getDrivers().get(i).getId());
                observableList.add("    " + "Имя водителя: " + auto.getDrivers().get(i).getFirstName());
                observableList.add("    " + "Фамилия водителя: " + auto.getDrivers().get(i).getLastName());
                observableList.add("    " + "Отчество водителя: " + auto.getDrivers().get(i).getFathersName());
            }
        }
        autoInformationListView.setItems(observableList);
    }
}
