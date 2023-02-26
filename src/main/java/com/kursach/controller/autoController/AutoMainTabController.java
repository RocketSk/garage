package com.kursach.controller.autoController;

import com.kursach.interfaceForController.TabGetWindow;
import com.kursach.model.Auto;
import com.kursach.service.AutoService;
import com.kursach.service.impl.AutoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoMainTabController extends TabGetWindow implements Initializable {

    private final static String URL_ADD_AUTO_FXML = "/view/auto/addAuto.fxml";
    private final static String URL_UPDATE_AUTO_FXML = "/view/auto/updateAuto.fxml";
    private final static String URL_DELETE_AUTO_FXML = "/view/auto/deleteAuto.fxml";
    private final static String URL_SEARCH_AUTO_BY_ID_FXML = "/view/auto/findAutoById.fxml";
    private final static String URL_ALL_AUTO_FXML = "/view/auto/autoList.fxml";
    private final static String ADD_AUTO_TITLE = "Добавить новый автомобиль";
    private final static String UPDATE_AUTO_TITLE = "Изменить автомобиль";
    private final static String DELETE_AUTO_TITLE = "Удалить автомобиль";
    private final static String SEARCH_AUTO_BY_ID_TITLE = "Поиск по ID";
    private final static String ALL_AUTO_TITLE = "Все автомобили";

    @FXML
    public TextField autoNumField;

    @FXML
    public ListView<String> autoInfoListView;

    private final AutoService autoService = new AutoServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void getAddAutoPage(ActionEvent event) {
        getWindow(URL_ADD_AUTO_FXML, ADD_AUTO_TITLE);
    }

    @FXML
    public void getUpdateAutoPage(ActionEvent event) {
        getWindow(URL_UPDATE_AUTO_FXML, UPDATE_AUTO_TITLE);
    }

    @FXML
    public void getDeleteAutoPage(ActionEvent event) {
        getWindow(URL_DELETE_AUTO_FXML, DELETE_AUTO_TITLE);
    }

    @FXML
    public void getAutoByIDPage(ActionEvent event) {
        getWindow(URL_SEARCH_AUTO_BY_ID_FXML, SEARCH_AUTO_BY_ID_TITLE);
    }

    @FXML
    public void getAllAutoPage(ActionEvent event) {
        getWindow(URL_ALL_AUTO_FXML, ALL_AUTO_TITLE);
    }

    @FXML
    public void showAutoInfo(ActionEvent event) {
        getAutoInfo();
    }

    private void getAutoInfo(){
        Auto auto = autoService.getByNum(autoNumField.getText());
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
