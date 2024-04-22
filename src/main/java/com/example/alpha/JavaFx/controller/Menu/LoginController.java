package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.controller.TaiKhoan.AccountType;
import com.example.alpha.JavaFx.model.LoaiNguoiDung;
import com.example.alpha.JavaFx.model.NguoiDung;
import com.example.alpha.JavaFx.model.Singleton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox.setItems(FXCollections.observableArrayList(AccountType.Admin,AccountType.Teacher,AccountType.Student));
        ChoiceBox.setValue(Singleton.getInstant().getViewFactory().getType());
        ChoiceBox.valueProperty().addListener(observable -> Singleton.getInstant().getViewFactory().setType(ChoiceBox.getValue()));
        button_login.setOnAction(event -> onLogin());
    }

    public void onLogin(){
        Singleton.getInstant().getViewFactory().getUsername().set(Textfield_username.getText());
        Singleton.getInstant().getViewFactory().getPassword().set(Textfield_password.getText());
        if(Singleton.getInstant().getViewFactory().getType() == AccountType.Admin){
            Singleton.getInstant().getViewFactory().showWorkPlaceWindow();
        }else if(Singleton.getInstant().getViewFactory().getType() == AccountType.Student){
            if(isValidAccount(Textfield_username.getText(),Textfield_password.getText(), LoaiNguoiDung.getRepository().getMaLoai(Singleton.getInstant().getViewFactory().getType().name())))
                Singleton.getInstant().getViewFactory().showStudentWindow();
        }else {
            if(isValidAccount(Textfield_username.getText(),Textfield_password.getText(), LoaiNguoiDung.getRepository().getMaLoai(Singleton.getInstant().getViewFactory().getType().name())))
                Singleton.getInstant().getViewFactory().showTeacherWindow();
        }
    }

    private boolean isValidAccount(String username, String password, String AccountType){
        System.out.println(AccountType);
        return username.equals(NguoiDung.getRepository().getUsername(username)) &&
                password.equals(NguoiDung.getRepository().getPassword(username ,password, AccountType)) ;
    }
}

