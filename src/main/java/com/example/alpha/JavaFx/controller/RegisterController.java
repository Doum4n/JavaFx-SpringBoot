package com.example.alpha.JavaFx.controller;

import static com.example.alpha.JavaFx.Load.LoadScence.LoadScence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.io.IOException;

@Controller
public class RegisterController {

    @FXML
    private Button Button_Register;

    @FXML
    private TextField TextField_ConfirmPassword;

    @FXML
    private TextField TextField_Firstname;

    @FXML
    private TextField TextField_Lasrname;

    @FXML
    private TextField TextField_Password;

    @FXML
    private TextField TextField_Username;

    @FXML
    void RegisterButtonAction(ActionEvent event) throws IOException {
        LoadScence(event, "login.fxml");
    }

}
