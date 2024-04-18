package com.example.alpha.JavaFx.controller.DanhGia;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSinnhVienHocKy;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class DSTop10percentController implements Initializable, setTable {
    @FXML
    private TableColumn<KqSinnhVienHocKy, String> Column_MaSV;

    @FXML
    private TableColumn<KqSinnhVienHocKy, String> Column_TenSV;

    @FXML
    private TableView<KqSinnhVienHocKy> TableView_DS;

    private FilteredList<KqSinnhVienHocKy> filteredList;

    private SortedList<KqSinnhVienHocKy> sortedList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        Model.getInstant().getDsTop10percentSVLop().getLop().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sv -> PhanLop.getRepository().getLop(sv.getMaSinhVien()).equals(newValue) &&
                    sv.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                    sv.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(filteredList);
            TableView_DS.setItems(sortedList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(DiemSV_HocKy.getRepository().findAll()));
        filteredList.setPredicate(sv -> PhanLop.getRepository().getLop(sv.getMaSinhVien()).equals(Model.getInstant().getDsTop10percentSVLop().getLop().get()));
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TableView_DS.comparatorProperty());
        TableView_DS.setItems(sortedList);
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Column_TenSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
