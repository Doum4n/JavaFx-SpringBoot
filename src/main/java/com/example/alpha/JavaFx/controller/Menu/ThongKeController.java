package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.view.ThongKe;
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
    private TextField TextFiled_Search;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button_KqSV_MH.setOnAction(event -> {
            Model.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemSV_MH);
        });

        Butoon_TKMonHoc.setOnAction(event -> {
            Model.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemLop_MonHoc);
        });

        Butoon_TKHocKy.setOnAction(event -> {
            Model.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemLop_HocKy);
        });

        Butoon_TKNamHoc.setOnAction(event -> {
            Model.getInstant().getViewThongKe().getThongKeProperty().set(ThongKe.TK_DiemLop_NamHoc);
        });

        TextFiled_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            Model.getInstant().getThongKe().getSearch().set(newValue);
        });
    }
}
