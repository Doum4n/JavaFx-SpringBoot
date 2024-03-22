package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.DatabaseConnection.DatabaseConnection;
import com.example.alpha.JavaFx.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.alpha.JavaFx.Load.LoadScence.LoadScence;

@Controller
public class LoginController implements Initializable {
    @FXML
    private ChoiceBox<AccountType> ChoiceBox;

    @FXML
    private Label Label_password;

    @FXML
    private Label Label_username;

    @FXML
    private Label Label_message;

    @FXML
    private PasswordField Textfield_password;

    @FXML
    private TextField Textfield_username;

    @FXML
    private Button button_login;
    @FXML
    private Button Button_Register;
//    DatabaseConnection connection = new DatabaseConnection(this, new WorkplaceController());

    @FXML
    public void LoginAction(ActionEvent event) throws SQLException, IOException {
    }

    @FXML
    void RegisterButtonAction(ActionEvent event) throws IOException {
        LoadScence(event,"Register.fxml");
    }

    public String getTextfield_password() {
        return Textfield_password.getText();
    }

    public String getTextfield_username() {
        return Textfield_username.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(event -> onLogin());
        ChoiceBox.setItems(FXCollections.observableArrayList(AccountType.Admin,AccountType.Teacher,AccountType.Student));
        ChoiceBox.setValue(Model.getInstant().getViewFactory().getType());
        ChoiceBox.valueProperty().addListener(observable -> Model.getInstant().getViewFactory().setType(ChoiceBox.getValue()));
    }

    public void onLogin(){
        if(Model.getInstant().getViewFactory().getType() == AccountType.Student){
            Model.getInstant().getViewFactory().showWorkPlaceWindow();
        }else if(Model.getInstant().getViewFactory().getType() == AccountType.Admin){
            Model.getInstant().getViewFactory().showAdminWindow();
        }
    }
}

