package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.SinhVien;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.jar.JarOutputStream;

@Controller
public class QLSinhVienController implements Initializable, setTable {
    @FXML
    private AnchorPane AnchorPane_QLSinhVien;

    @FXML
    private Button Button_Add;

    @FXML
    private Button Button_Delete;

    @FXML
    private Button Button_Update;

    @FXML
    private CheckBox CheckBox_Nam;

    @FXML
    private CheckBox CheckBox_Nu;

    @FXML
    private TableColumn<?, ?> Column_DiaChi;

    @FXML
    private TableColumn<?, ?> Column_Email;

    @FXML
    private TableColumn<?, ?> Column_GioiTinh;

    @FXML
    private TableColumn<?, ?> Column_HoTen;

    @FXML
    private TableColumn<?, ?> Column_MaDanToc;

    @FXML
    private TableColumn<?, ?> Column_MaSV;

    @FXML
    private TableColumn<?, ?> Column_MaTonGiao;

    @FXML
    private TableColumn<?, ?> Column_NgaySinh;

    @FXML
    private DatePicker DataPicker_NgaySinh;

    @FXML
    private TableView<SinhVienEntity> TableView_SinhVien;

    @FXML
    private TextArea TextArea_DiaChi;

    @FXML
    private TextField TextField_DanToc;

    @FXML
    private TextField TextField_Email;

    @FXML
    private TextField TextField_HoTen;

    @FXML
    private TextField TextField_MaSV;

    @FXML
    private TextField TextField_Search;

    @FXML
    private TextField TextField_TonGiao;
    private SinhVienEntity sinhVien;
    private ObservableList<SinhVienEntity> data = FXCollections.observableArrayList();
    private List<SinhVienEntity> sv;
    private final SimpleBooleanProperty isFemale = new SimpleBooleanProperty();

    public void setTableView() {
        setCellColumn();
        sv = SinhVien.getRepository().findAll();
        TableView_SinhVien.setItems(FXCollections.observableArrayList(sv));
    }

    private void addlistenerTableView(){
        data = FXCollections.observableArrayList(sv);
        TableView_SinhVien.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                int index = TableView_SinhVien.getSelectionModel().getSelectedIndex();
                sinhVien = data.get(index);

                TextField_HoTen.setText(sinhVien.getHoTen());
                TextField_Email.setText(sinhVien.getEmail());
                TextArea_DiaChi.setText(sinhVien.getDiaChi());
                TextField_DanToc.setText(sinhVien.getMaDanToc());
                TextField_TonGiao.setText(sinhVien.getMaTonGiao());

                isFemale.bind(CheckBox_Nu.selectedProperty());

                //Hien thi gioi tinh
                if(sinhVien.getGioiTinh()){
                    CheckBox_Nu.selectedProperty().set(true);
                    CheckBox_Nam.selectedProperty().set(false);
                }else{
                    CheckBox_Nam.selectedProperty().set(true);
                    CheckBox_Nu.selectedProperty().set(false);
                }
                TextField_MaSV.setText(sinhVien.getMaSinhVien());
                DataPicker_NgaySinh.setValue(sinhVien.getNgaySinh().toLocalDate());
            }
        });
        addListenerSearch();
    }

    public void setCellColumn() {
        Column_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        Column_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Column_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        Column_HoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        Column_MaDanToc.setCellValueFactory(new PropertyValueFactory<>("MaDanToc"));
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_MaTonGiao.setCellValueFactory(new PropertyValueFactory<>("MaTonGiao"));
        Column_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
    }

    private void load(){
        sv = SinhVien.getRepository().findAll();
        TableView_SinhVien.setItems(FXCollections.observableArrayList(sv));
    }


    private void addListenerSearch(){
        FilteredList<SinhVienEntity> filteredList = new FilteredList<>(data, b->true);
        TextField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sinhVienEntity -> {
                if(newValue.isBlank() || newValue.isEmpty()){
                    return true;
                }
                String id = newValue.toLowerCase();
                return sinhVienEntity.getMaSinhVien().toLowerCase().contains(id) ||
                        sinhVienEntity.getHoTen().toLowerCase().contains(id) ||
                        sinhVienEntity.getDiaChi().toLowerCase().contains(id) ||
                        sinhVienEntity.getNgaySinh().toString().toLowerCase().contains(id) ||
                        sinhVienEntity.getMaDanToc().toLowerCase().contains(id) ||
                        sinhVienEntity.getMaTonGiao().toLowerCase().contains(id);
            });
            SortedList<SinhVienEntity> sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            sortedList.comparatorProperty().bind(TableView_SinhVien.comparatorProperty());
            TableView_SinhVien.setItems(sortedList);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        addlistenerTableView();

        Button_Add.setOnAction(event -> {
            SinhVienEntity sinhVienEntity = new SinhVienEntity(TextField_MaSV.getText(),TextField_HoTen.getText(),isFemale.get(), Date.valueOf(DataPicker_NgaySinh.getValue()),TextArea_DiaChi.getText(), TextField_Email.getText(), TextField_DanToc.getText(), TextField_TonGiao.getText());
            SinhVien.getRepository().save(sinhVienEntity);
            load();
        });

        Button_Delete.setOnAction(event -> {
            SinhVien.getRepository().deleteById(TextField_MaSV.getText());
            load();
        });
    }
}
