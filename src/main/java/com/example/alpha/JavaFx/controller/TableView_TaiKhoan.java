package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.TaiKhoan;
import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class TableView_TaiKhoan implements Initializable, setTable{
    @FXML
    private TableView<NguoidungEntity> TableView_TaiKhoan;

    @FXML
    private TableColumn<?, ?> column_MaTK;

    @FXML
    private TableColumn<?, ?> column_Password;

    @FXML
    private TableColumn<?, ?> column_TenNguoiDung;

    @FXML
    private TableColumn<?, ?> column_TenTK;
    private List<NguoidungEntity> list;

    @Getter
    private List<NguoidungEntity> SinhVien = new ArrayList<>();
    @Getter
    private List<NguoidungEntity> GiaoVien = new ArrayList<>();

    @Override
    public void setTableView() {
        setCellColumn();
        list = TaiKhoan.getRepository().findAll();
    }

    @Override
    public void setCellColumn() {
        column_MaTK.setCellValueFactory(new PropertyValueFactory<>("MaNguoiDung"));
        column_TenTK.setCellValueFactory(new PropertyValueFactory<>("TenDangNhap"));
        column_TenNguoiDung.setCellValueFactory(new PropertyValueFactory<>("TenNguoiDung"));
        column_Password.setCellValueFactory(new PropertyValueFactory<>("MatKhau"));
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
        list = TaiKhoan.getRepository().findAll();
        list.forEach(user -> {
            if(Objects.equals(user.getMaLoai(), "2")){
                SinhVien.add(user);
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(SinhVien));
            }else if(Objects.equals(user.getMaLoai(),"3")){
                GiaoVien.add(user);
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(GiaoVien));
            }
        });

        Model.getInstant().getViewQuanLy().getTab_selected().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "Sinh viên")){
                list = TaiKhoan.getRepository().getSV();
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(list));
            }else {
                list = TaiKhoan.getRepository().getGV();
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(list));
            }
        });
    }
}
