package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.view.QuanLy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class QuanLyController implements Initializable {
    @FXML
    private Button button_Diem;

    @FXML
    private Button button_KetQua;

    @FXML
    private Button button_PhanCong;

    @FXML
    private Button button_TaiKhoan;

    @FXML
    private Button button_QLSinhVien;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_QLSinhVien.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.QLSinhVien));
        button_Diem.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.DiemThi));
//        button_PhanLop.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.PhanLop));
//        button_QLGiaoVien.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.QLGiaoVien));
        button_PhanCong.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.DiemQT));
        button_TaiKhoan.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.TaiKhoan));
//        button_PhanCong.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.PhanCong));
    }
}
