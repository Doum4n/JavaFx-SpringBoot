package com.example.alpha.JavaFx.role_admin.controller.quan_ly.SinhVien;

import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemSinhVien.ButtonCellSV;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.SinhVien;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

@Controller
public class QLSinhVienController implements Initializable, setTable {
    @FXML
    private AnchorPane AnchorPane_QLSinhVien;

    @FXML
    private TableColumn<?, ?> Column_DiaChi;

    @FXML
    private TableColumn<?, ?> Column_Email;

    @FXML
    private TableColumn<SinhVienEntity, String> Column_GioiTinh;

    @FXML
    private TableColumn<?, ?> Column_HoTen;

    @FXML
    private TableColumn<?, ?> Column_MaSV;

    @FXML
    private TableColumn<?, ?> Column_NgaySinh;

    @FXML
    private TableColumn<SinhVienEntity, Button> ColumnX;

    @FXML
    private TableView<SinhVienEntity> TableView_SinhVien;

    @FXML
    private TextField TextField_Search;

    private SinhVienEntity sinhVien;
    private ObservableList<SinhVienEntity> data = FXCollections.observableArrayList();
    private final SimpleBooleanProperty isFemale = new SimpleBooleanProperty();

    private SortedList<SinhVienEntity> sortedList;

    @Override
    public void setTableView() {
        setCellColumn();
        List<SinhVienEntity> sv = SinhVien.getRepository().findAll();
        data = FXCollections.observableArrayList(sv);
        TableView_SinhVien.setItems(FXCollections.observableArrayList(data));
    }

    @Override
    public void addListenerTableView(){
        /*data = FXCollections.observableArrayList(sv);
        Model.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {
            data.stream().filter(sinhVienEntity ->
                    sinhVienEntity.getMaSinhVien().equals(Model.getInstant().getDiemSinhVien().getSvSelected().get())
            ).findFirst().ifPresent(sinhVienEntity -> {
                TextField_HoTen.setText(sinhVienEntity.getHoTen());
                TextField_Email.setText(sinhVienEntity.getEmail());
                TextArea_DiaChi.setText(sinhVienEntity.getDiaChi());

                isFemale.bind(CheckBox_Nu.selectedProperty());

                //Hien thi gioi tinh
                if (sinhVienEntity.getGioiTinh()) {
                    CheckBox_Nu.selectedProperty().set(true);
                    CheckBox_Nam.selectedProperty().set(false);

                    CheckBox_Nam.setDisable(true);
                    CheckBox_Nu.setDisable(false);
                } else {
                    CheckBox_Nam.selectedProperty().set(true);
                    CheckBox_Nu.selectedProperty().set(false);

                    CheckBox_Nu.setDisable(true);
                    CheckBox_Nam.setDisable(false);
                }
                TextField_MaSV.setText(sinhVienEntity.getMaSinhVien());
                DataPicker_NgaySinh.setValue(sinhVienEntity.getNgaySinh().toLocalDate());
            });
        });
        addListenerSearch();*/
    }

    @Override
    public void setCellColumn() {
        Column_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        Column_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Column_GioiTinh.setCellValueFactory(param -> {
            if(param.getValue().getGioiTinh()) {
                return new ReadOnlyObjectWrapper<>("Nữ");
            }
            return new ReadOnlyObjectWrapper<>("Nam");
        });
        Column_HoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        ColumnX.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(new ButtonCellSV().getButton()));
    }

    @Override
    public void addListenerSearch(){
        FilteredList<SinhVienEntity> filteredList = new FilteredList<>(data, b->true);
        TextField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sinhVienEntity -> {
                if(newValue.isBlank() || newValue.isEmpty()){
                    return true;
                }
                String id = newValue.toLowerCase();
                return sinhVienEntity.getMaSinhVien().toLowerCase().equals(id);
            });
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            sortedList.comparatorProperty().bind(TableView_SinhVien.comparatorProperty());
            TableView_SinhVien.setItems(sortedList);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
//        addListenerTableView();
        addListenerSearch();
    }
}