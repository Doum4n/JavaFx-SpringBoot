package com.example.alpha.JavaFx.controller.DanhGia;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.DiemSV_CaNam;
import com.example.alpha.JavaFx.model.Lop;
import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DSKhoaLuanController implements Initializable, setTable {

    @FXML
    private TableColumn<?, ?> Column_DiemTK;

    @FXML
    private TableColumn<?, ?> Column_MaSV;

    @FXML
    private TableColumn<?, ?> Column_TenSV;

    @FXML
    private ComboBox<?> ComboBox_LopNamCuoi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setTableView() {
        List<KqSinhVienCanamEntity> kq = DiemSV_CaNam.getRepository().findAll();
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
