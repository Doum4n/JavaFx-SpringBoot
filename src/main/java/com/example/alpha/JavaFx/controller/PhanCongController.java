package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.GiaoVien.PhanCong;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PhanCongController implements Initializable, setTable {
    @FXML
    private TableColumn<?, ?> Column_MaGV;

    @FXML
    private TableColumn<?, ?> Column_MaLop;

    @FXML
    private TableColumn<?, ?> Column_MaMH;

    @FXML
    private TableColumn<?, ?> Column_MaNamHoc;

    @FXML
    private TableView<PhancongEntity> TableView_PhanCong;

    @Override
    public void setTableView() {
        setCellColumn();
        List<PhancongEntity> oj = PhanCong.getRepository().findAll();
        TableView_PhanCong.setItems(FXCollections.observableArrayList(oj));
    }

    @Override
    public void setCellColumn() {
        Column_MaLop.setCellValueFactory(new PropertyValueFactory<>("MaLop"));
        Column_MaMH.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
        Column_MaNamHoc.setCellValueFactory(new PropertyValueFactory<>("MaNamHoc"));
        Column_MaGV.setCellValueFactory(new PropertyValueFactory<>("MaGiaoVien"));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
    }
}
