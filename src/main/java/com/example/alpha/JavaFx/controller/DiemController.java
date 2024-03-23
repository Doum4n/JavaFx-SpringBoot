package com.example.alpha.JavaFx.controller;

import com.example.alpha.repository.DiemEntityRepository;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class DiemController implements Initializable, setTable {
    @FXML
    private TableColumn<?, ?> Column_Diem;

    @FXML
    private TableColumn<?, ?> Column_LanThi;

    @FXML
    private TableColumn<?, ?> Column_MaHocKy;

    @FXML
    private TableColumn<?, ?> Column_MaLoai;

    @FXML
    private TableColumn<?, ?> Column_MaLop;

    @FXML
    private TableColumn<?, ?> Column_MaMonHoc;

    @FXML
    private TableColumn<?, ?> Column_MaNamHoc;

    @FXML
    private TableColumn<?, ?> Column_MaSV;

    @FXML
    private TableView<DiemEntity> TableView_Diem;

    private DiemEntityRepository repository;


    @Override
    public void setTableView() {
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSV"));
        Column_Diem.setCellValueFactory(new PropertyValueFactory<>("Diem"));
        Column_LanThi.setCellValueFactory(new PropertyValueFactory<>("LanThi"));
        Column_MaHocKy.setCellValueFactory(new PropertyValueFactory<>("MaHocKy"));
        Column_MaLoai.setCellValueFactory(new PropertyValueFactory<>("MaLoai"));
        Column_MaLop.setCellValueFactory(new PropertyValueFactory<>("MaLop"));
        Column_MaMonHoc.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
        Column_MaNamHoc.setCellValueFactory(new PropertyValueFactory<>("MaNamHoc"));
    }

    @Override
    public void setCellColumn() {
        setTableView();
        List<DiemEntity> ob = repository.findAll();
        TableView_Diem.setItems(FXCollections.observableArrayList(ob));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
    }

    @Autowired
    public void setRepository(DiemEntityRepository repository) {
        this.repository = repository;
    }
}
