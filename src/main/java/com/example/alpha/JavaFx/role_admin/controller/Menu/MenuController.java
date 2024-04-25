package com.example.alpha.JavaFx.role_admin.controller.Menu;

import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.view.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuController implements Initializable {
    @FXML
    private AnchorPane AnchorPane_Menu;

    @FXML
    private Button Button_LogOut;

    @FXML
    private Button button_QuanLy;

    @FXML
    private Button button_TongKe;

    @FXML
    private Button button_DanhGia;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_QuanLy.setOnAction(event -> addListenerQuanLy());
        button_DanhGia.setOnAction(event -> addListenerTongQuan());
        button_TongKe.setOnAction(event -> addListenerThongKe());
        Button_LogOut.setOnAction(event -> Singleton.getInstant().getViewFactory().showLoginWindow());
    }

    private void addListenerQuanLy(){
        Singleton.getInstant().getViewFactory().getMenuProperty().set(Menu.QuanLy);
    }

    private void addListenerTongQuan(){
        Singleton.getInstant().getViewFactory().getMenuProperty().set(Menu.DanhGia);
    }

    private void addListenerThongKe(){
        Singleton.getInstant().getViewFactory().getMenuProperty().set(Menu.ThongKe);
    }

}
