package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.controller.Menu.LoginController;
import com.example.alpha.JavaFx.model.LoaiNguoiDung;
import com.example.alpha.JavaFx.model.NguoiDung;
import com.example.alpha.JavaFx.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    @FXML
    private Button Button_valid;

    @FXML
    private TextField TextFiled_newPassord;

    @FXML
    private TextField TextFiled_oldPassord;

    @FXML
    private TextField TextFiled_validPassord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button_valid.setOnAction(event -> {
            if(isValidPassword(Singleton.getInstant().getViewFactory().getUsername().get(), TextFiled_oldPassord.getText(), LoaiNguoiDung.getRepository().getMaLoai(Singleton.getInstant().getViewFactory().getType().name())))
                NguoiDung.getRepository().updatePassword(
                        Singleton.getInstant().getViewFactory().getUsername().get(),
                        NguoiDung.getRepository().getPassword(Singleton.getInstant().getViewFactory().getUsername().get() , TextFiled_oldPassord.getText(), LoaiNguoiDung.getRepository().getMaLoai(Singleton.getInstant().getViewFactory().getType().name())),
                        TextFiled_newPassord.getText(),
                        LoaiNguoiDung.getRepository().getMaLoai(Singleton.getInstant().getViewFactory().getType().name())
                );

            Stage stage = (Stage) Button_valid.getScene().getWindow();
            stage.close();
        });
    }

    private boolean isValidPassword(String username, String password, String AccountType){
        return password.equals(NguoiDung.getRepository().getPassword(username ,password, AccountType)) &&
                TextFiled_newPassord.getText().equals(TextFiled_validPassord.getText());
    }
}
