package com.example.alpha.JavaFx.role_admin.controller.DanhGia;

import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.PhanLop;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
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
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

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
        Singleton.getInstant().getDsTop10percentSVLop().getLop().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sv -> PhanLop.getRepository().getLop(sv.getMaSinhVien()).equals(newValue) &&
                    sv.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get()) &&
                    sv.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(filteredList);
            TableView_DS.setItems(sortedList);
        });

        Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sv -> PhanLop.getRepository().getLop(sv.getMaSinhVien()).equals(Singleton.getInstant().getDsTop10percentSVLop().getLop().get()) &&
                    sv.getMaHocKy().equals(newValue) &&
                    sv.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(filteredList);
            TableView_DS.setItems(sortedList);
        });

        Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sv -> PhanLop.getRepository().getLop(sv.getMaSinhVien()).equals(Singleton.getInstant().getDsTop10percentSVLop().getLop().get()) &&
                    sv.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get()) &&
                    sv.getMaNamHoc().equals(newValue));
            sortedList = new SortedList<>(filteredList);
            TableView_DS.setItems(sortedList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(DiemSV_HocKy.getRepository().findAll()));

        int Top10 = (int)(filteredList.size()*0.1);

        filteredList.setPredicate(sv -> PhanLop.getRepository().getLop(sv.getMaSinhVien()).equals(Singleton.getInstant().getDsTop10percentSVLop().getLop().get()) &&
                sv.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get()) &&
                sv.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()) &&
                isTop10(sv, Top10));
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TableView_DS.comparatorProperty());
        TableView_DS.setItems(sortedList);
    }

    private boolean isTop10(KqSinnhVienHocKy kqSV, int Top10){
        List<KqSinnhVienHocKy> sortedList = filteredList.stream().sorted(Comparator.comparing(KqSinnhVienHocKy::getDiemTkHocKy)).toList();
        for(int i=0; i<Top10;i++){
            if(sortedList.contains(kqSV)){
                return true;
            }
        }
        return false;
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
