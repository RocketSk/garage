package com.kursach.interfaceForController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class TabGetWindow {

    protected void getWindow(String windowUrl, String title){
        try{
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(windowUrl)));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
