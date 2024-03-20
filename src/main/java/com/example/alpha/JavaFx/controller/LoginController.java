package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.DatabaseConnection.DatabaseConnection;
import com.example.alpha.JavaFx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.alpha.JavaFx.Load.LoadScence.LoadScence;

@Controller
public class LoginController implements Initializable {
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
        /*if(Textfield_username.getText().isBlank()){
//            accountUser = loginService.Login(Textfield_username.getText(), Textfield_password.getText());
            Label_message.setText("Please enter username!");
            return;
        }
        if(Textfield_password.getText().isBlank()){
            Label_message.setText("Please enter password!");
            return;
        }
//        loginConfirmation();
        LoadScence(event, "workplace.fxml");*/
    }

    @FXML
    void RegisterButtonAction(ActionEvent event) throws IOException {
        LoadScence(event,"Register.fxml");
    }

    public void loginConfirmation() throws SQLException {
        Connection connectiondb = DatabaseConnection.getConnection();
        String sql = "select * from mat_hang";

        Statement statement = connectiondb.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            Label_message.setText("Success!");
        }
    }

    public static void loadView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/login.fxml"))));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public String getTextfield_password() {
        return Textfield_password.getText();
    }

    public String getTextfield_username() {
        return Textfield_username.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(event -> Model.getInstant().getViewFactory().showWorkPlaceWindow());
    }
}

