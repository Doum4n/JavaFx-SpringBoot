package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuController implements Initializable {
    @FXML
    private AnchorPane AnchorPane_Menu;

    @FXML
    private Button button_QuanLy;

    @FXML
    private Button button_TongKe;

    @FXML
    private Button button_TongQuan;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_QuanLy.setOnAction(event -> addListenerQuanLy());
        button_TongQuan.setOnAction(event -> addListenerTongQuan());
        button_TongKe.setOnAction(event -> addListenerThongKe());
    }

    private void addListenerQuanLy(){
        Model.getInstant().getViewFactory().getStringProperty().set("QuanLy");
    }

    private void addListenerTongQuan(){
        Model.getInstant().getViewFactory().getStringProperty().set("TongQuan");
    }

    private void addListenerThongKe(){
        Model.getInstant().getViewFactory().getStringProperty().set("ThongKe");
    }

}
