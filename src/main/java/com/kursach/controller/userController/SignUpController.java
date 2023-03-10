package com.kursach.controller.userController;

import com.kursach.model.User;
import com.kursach.service.UserService;
import com.kursach.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    private final static String OK_TITLE = "Успешно";
    private final static String WRONG_TITLE = "Ошибка";
    private final static String SIGN_UP_CONTENT = "Регистрация прошла успешно";
    private final static String WRONG_PASS_REPLAY_CONTENT = "Пароли не совпадают";
    private final static String WRONG_USERNAME_CONTENT = "Пользователь с таким именем \n уже существует";

    private final UserService userService = new UserServiceImpl();

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repPasswordField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void signUp(ActionEvent event) {
        if (passwordField.getText().equals(repPasswordField.getText())) {
            if (userService.getUser(loginField.getText()) == null) {
                userService.signUpUser(User.builder()
                        .username(loginField.getText())
                        .password(passwordField.getText())
                        .build());
                showAlert(Alert.AlertType.INFORMATION, OK_TITLE, SIGN_UP_CONTENT);
                Stage stage = (Stage) passwordField.getScene().getWindow();
                stage.close();
            } else {
                showAlert(Alert.AlertType.WARNING, WRONG_TITLE, WRONG_USERNAME_CONTENT);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, WRONG_TITLE, WRONG_PASS_REPLAY_CONTENT);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
