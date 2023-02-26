package com.kursach.controller.autoController;

import com.kursach.model.Auto;
import com.kursach.service.AutoService;
import com.kursach.service.impl.AutoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FindAutoByIdController {

    @FXML
    public TextField autoIdField;
    @FXML
    public ListView<String> autoInfoListView;

    private final AutoService autoService = new AutoServiceImpl();


    @FXML
    public void showAutoInfo(ActionEvent event) {
        getAutoInfo();
    }

    private void getAutoInfo(){
        Auto auto = autoService.getById(Integer.parseInt(autoIdField.getText()));
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
        autoInfoListView.setItems(observableList);
    }

}
