package com.example.alpha.JavaFx.role_admin.controller.Menu;

import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.view.DanhGia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class DanhGiaController implements Initializable, setTable {
    @FXML
    private Button button_DSHocBong;

    @FXML
    private Button button_DSKhoaLuan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_DSHocBong.setOnAction(event -> Singleton.getInstant().getViewDanhGia().getDanhGiaProperty().set(DanhGia.DSHocBong));
        button_DSKhoaLuan.setOnAction(event -> Singleton.getInstant().getViewDanhGia().getDanhGiaProperty().set(DanhGia.DSKhoaLuan));
    }

    @Override
    public void setTableView() {

    }

    @Override
    public void setCellColumn() {

    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
