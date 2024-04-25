package com.example.alpha.JavaFx.role_admin.controller.Menu;

import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.view.ThongKe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class ThongKeController implements Initializable {
    @FXML
    private Button Butoon_TKHocKy;

    @FXML
    private Button Butoon_TKMonHoc;

    @FXML
    private Button Button_KqSV_MH;

    @FXML
    private Button Butoon_TKNamHoc;

    @FXML
    private TextField TextFiled_Search_SV;

    @FXML
    private TextField TextField_Search_Lop;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button_KqSV_MH.setOnAction(event -> {
            Singleton.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemSV_MH);
        });

        Butoon_TKMonHoc.setOnAction(event -> {
            Singleton.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemLop_MonHoc);
        });

        Butoon_TKHocKy.setOnAction(event -> {
            Singleton.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemLop_HocKy);
        });

        Butoon_TKNamHoc.setOnAction(event -> {
            Singleton.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemLop_NamHoc);
        });

        TextFiled_Search_SV.textProperty().addListener((observable, oldValue, newValue) -> {
            Singleton.getInstant().getThongKe().getSearch_SV().set(newValue);
        });

        TextField_Search_Lop.textProperty().addListener((observable, oldValue, newValue) -> {
            Singleton.getInstant().getThongKe().getSearch_Lop().set(newValue);
        });
    }
}
