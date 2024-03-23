package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.SinhVien;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class QLSinhVienController implements Initializable, setTable {
    @FXML
    private AnchorPane AnchorPane_QLSinhVien;

    @FXML
    private Button Button_Add;

    @FXML
    private Button Button_Delete;

    @FXML
    private Button Button_Search;

    @FXML
    private Button Button_Update;

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
    private Label Label_DiaChi;

    @FXML
    private Label Label_DienThoai;

    @FXML
    private Label Label_MaKH;

    @FXML
    private Label Label_TenKH;

    @FXML
    private TextField TextField_DiaChi;

    @FXML
    private TextField TextField_DienThoai;

    @FXML
    private TextField TextField_MaKH;

    @FXML
    private TextField TextField_Search;

    @FXML
    private TextField TextField_TenKH;

    @FXML
    protected TableView<SinhVienEntity> TableView_SinhVien;

    public void setTableView() {
        setCellColumn();
        List<SinhVienEntity> myObjects = SinhVien.getRepository().findAll(); /*getHttpConnection.getData("http://localhost:8080/SinhVien/all").readValue(getHttpConnection.getResponse(), new TypeReference<>() {});*/
        TableView_SinhVien.setItems(FXCollections.observableArrayList(myObjects));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
    }
}
