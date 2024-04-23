package com.example.alpha.JavaFx.controller.TaiKhoan;

import com.example.alpha.JavaFx.model.LoaiNguoiDung;
import com.example.alpha.JavaFx.model.NguoiDung;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class TaiKhoanController implements Initializable{
    @FXML
    private Tab Tab_GV;

    @FXML
    private Tab Tab_SV;
    @FXML
    private TabPane Tabpane_TaiKhoan;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_update;

    @FXML
    private TextField TextFiled_Password;

    @FXML
    private Label Label_Username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tabpane_TaiKhoan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.getText().equals("Sinh viên")){
                Singleton.getInstant().getViewQuanLy().getTab_selected().set("Sinh viên");
            }else {
                Singleton.getInstant().getViewQuanLy().getTab_selected().set("Giáo viên");
            }
        });

        TextFiled_Password.textProperty().bindBidirectional(Singleton.getInstant().getQuanLyTaiKhoan().getPassword());
        Label_Username.textProperty().bind(Singleton.getInstant().getQuanLyTaiKhoan().getUsername());

        button_update.setOnAction(event -> {
            NguoiDung.getRepository().updatePassword(Label_Username.getText(),
                    TextFiled_Password.getText(),
                    Singleton.getInstant().getQuanLyTaiKhoan().getAccountType().get()
            );
        });
    }
}
