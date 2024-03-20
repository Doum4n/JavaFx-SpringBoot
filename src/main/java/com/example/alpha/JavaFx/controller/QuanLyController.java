package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
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
    private Button button_Drl;

    @FXML
    private Button button_HocLuc;

    @FXML
    private Button button_KetQua;

    @FXML
    private Button button_PhanLop;

    @FXML
    private Button button_QLSinhVien;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_QLSinhVien.setOnAction(event -> Model.getInstant().getViewFactory().getStringProperty().set("QLSinhVien"));
    }
}
