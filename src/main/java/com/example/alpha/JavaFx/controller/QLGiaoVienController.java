package com.example.alpha.JavaFx.controller;

import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class QLGiaoVienController implements Initializable {
    @FXML
    private TableColumn<?, ?> Column_DiaChi;

    @FXML
    private TableColumn<?, ?> Column_DienThoai;

    @FXML
    private TableColumn<?, ?> Column_MaGV;

    @FXML
    private TableColumn<?, ?> Column_MaMH;

    @FXML
    private TableColumn<?, ?> Column_TenGV;

    @FXML
    private TableView<GiaovienEntity> TableView_GiaoVien;

    @FXML
    private Label label;

    public void setCellColumnMatHangs() {
        Column_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        Column_DienThoai.setCellValueFactory(new PropertyValueFactory<>("DienThoai"));
        Column_MaGV.setCellValueFactory(new PropertyValueFactory<>("MaGV"));
        Column_MaMH.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        Column_TenGV.setCellValueFactory(new PropertyValueFactory<>("TenGV"));
    }
    public void setTableView_MatHang() throws SQLException, JsonProcessingException {
        setCellColumnMatHangs();
        List<GiaovienEntity> myObjects = null; /*getHttpConnection.getData("http://localhost:8080/GiaoVien/all").readValue(getHttpConnection.getResponse(), new TypeReference<>() {});*/
        TableView_GiaoVien.setItems(FXCollections.observableArrayList(myObjects));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setTableView_MatHang();
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
