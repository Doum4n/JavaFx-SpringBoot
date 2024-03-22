package com.example.alpha.JavaFx.controller;

import com.example.alpha.Spring_boot.assignment.PhanlopEntity;
import com.example.alpha.Spring_boot.repository.PhancongEntityRepository;
import com.example.alpha.Spring_boot.repository.PhanlopEntityRepository;
import jakarta.validation.spi.ValidationProvider;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class PhanLopController implements setTable, Initializable {
    @FXML
    private TableView<PhanlopEntity> TableView_PhanLop;

    @FXML
    private ChoiceBox<String> ChoiceBox_Lop;

    @FXML
    private ChoiceBox<String> ChoiceBox_NamHoc;

    @FXML
    private TableColumn<?, ?> column_MaLop;

    @FXML
    private TableColumn<?, ?> column_MaNamHoc;

    @FXML
    private TableColumn<?, ?> column_MaSV;

    private PhanlopEntityRepository repository;

    @Override
    public void setTableView() {
        setCellColumn();
        if(repository != null) {
            List<PhanlopEntity> ob = repository.findAll();
            TableView_PhanLop.setItems(FXCollections.observableArrayList(ob));
        }
    }

    @Override
    public void setCellColumn() {
        column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSV"));
        column_MaLop.setCellValueFactory(new PropertyValueFactory<>("MaLop"));
        column_MaNamHoc.setCellValueFactory(new PropertyValueFactory<>("MaNamHoc"));
    }

    @Autowired
    public void setPhanlopEntityRepository(PhanlopEntityRepository phanlopEntityRepository) {
        this.repository = phanlopEntityRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        ChoiceBox_NamHoc.setItems(FXCollections.observableArrayList("Năm 1","Năm 2","Năm 3","Năm 4","Năm 5","Năm 6"));
        ChoiceBox_Lop.setItems(FXCollections.observableArrayList("Kỹ thuật phần mềm","Công nghệ thông tin"));
    }
}
