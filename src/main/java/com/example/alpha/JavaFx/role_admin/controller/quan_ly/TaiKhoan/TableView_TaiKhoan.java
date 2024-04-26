package com.example.alpha.JavaFx.role_admin.controller.quan_ly.TaiKhoan;

import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.TaiKhoan;
import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class TableView_TaiKhoan implements Initializable, setTable {
    @FXML
    private TableView<NguoidungEntity> TableView_TaiKhoan;

    @FXML
    private TableColumn<?, ?> column_Password;

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
        TableView_TaiKhoan.setItems(FXCollections.observableList(list));
    }

    @Override
    public void setCellColumn() {
        column_TenTK.setCellValueFactory(new PropertyValueFactory<>("TenDangNhap"));
        column_Password.setCellValueFactory(new PropertyValueFactory<>("MatKhau"));
    }

    @Override
    public void addListenerTableView() {
        TableView_TaiKhoan.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                int index = TableView_TaiKhoan.getSelectionModel().getSelectedIndex();
                NguoidungEntity user = list.get(index);
                Singleton.getInstant().getQuanLyTaiKhoan().getUsername().set(user.getTenDangNhap());
                Singleton.getInstant().getQuanLyTaiKhoan().getPassword().set(user.getMatKhau());
                Singleton.getInstant().getQuanLyTaiKhoan().getAccountType().set(user.getMaLoai());
            }
        });
    }

    @Override
    public void addListenerSearch() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        addListenerTableView();
        list = TaiKhoan.getRepository().findAll();
        list.forEach(user -> {
            if(Objects.equals(user.getMaLoai(), "3")){
                SinhVien.add(user);
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(SinhVien));
            }else if(Objects.equals(user.getMaLoai(),"2")){
                GiaoVien.add(user);
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(GiaoVien));
            }
        });

        Singleton.getInstant().getViewQuanLy().getTab_selected().addListener((observable, oldValue, newValue) -> {
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
