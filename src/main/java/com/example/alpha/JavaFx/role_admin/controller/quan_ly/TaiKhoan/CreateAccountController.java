package com.example.alpha.JavaFx.role_admin.controller.quan_ly.TaiKhoan;

import com.example.alpha.JavaFx.role_admin.model.LoaiNguoiDung;
import com.example.alpha.JavaFx.role_admin.model.NguoiDung;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.Status;
import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.poi.ss.formula.functions.Single;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML
    private Button Button_create;

    @FXML
    private ChoiceBox<String> ChoiceBox_accountType;

    @FXML
    private TextField TextFiled_password;

    @FXML
    private TextField TextFiled_uername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ChoiceBox_accountType.setItems(FXCollections.observableArrayList(LoaiNguoiDung.getRepository().getTenLoai()));

        Button_create.setOnAction(event -> {
            NguoiDung.getRepository().save(new NguoidungEntity(LoaiNguoiDung.getRepository().getMaLoai(ChoiceBox_accountType.getValue()), TextFiled_uername.getText(), TextFiled_password.getText()));
            Singleton.getInstant().getQuanLyTaiKhoan().getCreateAccountType().set(LoaiNguoiDung.getRepository().getMaLoai(ChoiceBox_accountType.getValue()));
            Singleton.getInstant().getQuanLyTaiKhoan().getCreate_username().set(TextFiled_uername.getText());
            Singleton.getInstant().getQuanLyTaiKhoan().getCreate_password().set(TextFiled_password.getText());
            Singleton.getInstant().getQuanLyTaiKhoan().getIsCreate().set(true);
            Singleton.getInstant().getQuanLyTaiKhoan().getIsCreate().set(false);
        });
    }
}
