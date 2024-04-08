package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.DiemQT;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien;
import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DiemQTController implements Initializable, setTable {
    @FXML
    private TableColumn<?, ?> Column_DiemQT;

    @FXML
    private TableColumn<DiemQTEntity, String> Column_MaSV;

    @FXML
    private TableColumn<DiemQTEntity, String> Column_TenSV;

    @FXML
    private TableView<DiemQTEntity> TableView_Diem;

    private ObservableList<DiemQTEntity> data = FXCollections.observableArrayList();

    private FilteredList<DiemQTEntity> filteredList;
    private SortedList<DiemQTEntity> sortedList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaHK().equals(newValue) &&
                    diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(filteredList);
            TableView_Diem.setItems(sortedList);
        });

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaNH().equals(newValue) &&
                    diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()));
            sortedList = new SortedList<>(filteredList);
            TableView_Diem.setItems(sortedList);
        });

        Model.getInstant().getViewQuanLy().getLopSelected().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity ->{
                System.out.println(diemQTEntity.getMaNH()+":"+Model.getInstant().getViewFactory().getNamHoc().get()+diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
                System.out.println(diemQTEntity.getMaHK()+":"+Model.getInstant().getViewFactory().getHocky().get()+diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()));
                System.out.println(PhanLop.getRepository().getLop(diemQTEntity.getMaSV())+":"+newValue+Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.getMaSV()), newValue));
                System.out.println(diemQTEntity.getMaMH()+":"+Model.getInstant().getViewQuanLy().getMaMHSelected().get()+diemQTEntity.getMaMH().equals(Model.getInstant().getViewQuanLy().getMaMHSelected().get()));
                return diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                    diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                    Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.getMaSV()), newValue) &&
                    diemQTEntity.getMaMH().equals(Model.getInstant().getViewQuanLy().getMaMHSelected().get());
            });
            sortedList = new SortedList<>(filteredList);
            TableView_Diem.setItems(sortedList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<DiemQTEntity> diem = DiemQT.getRepository().findAll();
        data = FXCollections.observableArrayList(diem);
        TableView_Diem.setItems(data);
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaSV()));
        Column_TenSV.setCellValueFactory(param -> new SimpleStringProperty(SinhVien.getRepository().getByHoTen(param.getValue().getMaSV())));
        Column_DiemQT.setCellValueFactory(new PropertyValueFactory<>("DiemQT"));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
