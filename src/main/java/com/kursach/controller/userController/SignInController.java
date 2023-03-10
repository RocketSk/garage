package com.kursach.controller.userController;

import com.kursach.Main;
import com.kursach.interfaceForController.TabGetWindow;
import com.kursach.model.User;
import com.kursach.service.UserService;
import com.kursach.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController extends TabGetWindow {

    private final static String OK_TITLE = "Успешно";
    private final static String WRONG_TITLE = "Ошибка";

    private final static String SIGN_IN_CONTENT = "Вход выполнен";
    private final static String WRONG_SIGN_IN_CONTENT = "Неправильный логин или пароль";

    private final UserService userService = new UserServiceImpl();

    private final static String URL_SIGN_UP_FXML = "/view/security/signUp.fxml";

    private final static String SIGN_UP_TITLE = "Регистрация";



    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void signIn(ActionEvent event) throws IOException {
        signIn();
    }

    @FXML
    void signUp(ActionEvent event) {
        getWindow(URL_SIGN_UP_FXML, SIGN_UP_TITLE);
    }

    private void signIn() throws IOException {
        User user = userService.getUser(loginField.getText());
        if(user != null && user.getPassword().equals(passwordField.getText())){
            Stage stage = (Stage) loginField.getScene().getWindow();
            showAlert(Alert.AlertType.INFORMATION, OK_TITLE, SIGN_IN_CONTENT);
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Приложение Москалев");
            stage.show();
        } else {
            showAlert(Alert.AlertType.WARNING, WRONG_TITLE, WRONG_SIGN_IN_CONTENT);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
