package com.example.alpha.JavaFx.role_admin.controller.Menu;

import com.example.alpha.JavaFx.controller.quan_ly.TaiKhoan.AccountType;
import com.example.alpha.JavaFx.role_admin.model.LoaiNguoiDung;
import com.example.alpha.JavaFx.role_admin.model.NguoiDung;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import jakarta.validation.constraints.NotNull;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private ChoiceBox<AccountType> ChoiceBox;

    @FXML
    private PasswordField Textfield_password;

    @FXML
    private TextField Textfield_username;

    @FXML
    private Button button_login;

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
        return password.equals(NguoiDung.getRepository().getPassword(username ,password, AccountType)) ;
    }
}

