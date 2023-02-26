package com.kursach.controller.autoController;

import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.service.AutoService;
import com.kursach.service.impl.AutoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AutoListController implements Initializable {

    @FXML
    private TableColumn<Driver, String> autoIdCol;

    @FXML
    private TableColumn<Driver, String> autoBrandCol;

    @FXML
    private TableColumn<Driver, String> autoModelCol;

    @FXML
    private TableColumn<Driver, Integer> autoFuelCol;

    @FXML
    private TableColumn<Driver, String> autoNumCol;

    @FXML
    private TableView<Auto> autoTableView;

    @FXML
    private AnchorPane rootPane;

    AutoService service = new AutoServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showAuto();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAuto() throws SQLException {
        ObservableList<Auto> observableList = FXCollections.observableArrayList(service.getAll());

        autoIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        autoBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        autoModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        autoFuelCol.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        autoNumCol.setCellValueFactory(new PropertyValueFactory<>("num"));

        autoTableView.setItems(observableList);
    }
}
