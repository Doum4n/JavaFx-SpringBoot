package com.example.alpha.JavaFx.role_admin.controller.quan_ly.TaiKhoan;

import com.example.alpha.JavaFx.role_admin.model.NguoiDung;
import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.TaiKhoan;
import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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

    private ObservableList<NguoidungEntity> data = FXCollections.observableArrayList();

    @Getter
    private List<NguoidungEntity> SinhVien = new ArrayList<>();
    @Getter
    private List<NguoidungEntity> GiaoVien = new ArrayList<>();

    @Override
    public void setTableView() {
        setCellColumn();
        data = FXCollections.observableArrayList(TaiKhoan.getRepository().findAll());
        TableView_TaiKhoan.setItems(data);
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
                NguoidungEntity user;
                if(Singleton.getInstant().getViewQuanLy().getTab_selected().get().equals("Sinh viên")) {
                    user = SinhVien.get(index);
                }else {
                    user = GiaoVien.get(index);
                }
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
        data.forEach(user -> {
            if(Objects.equals(user.getMaLoai(), "3")){
                SinhVien.add(user);
            }else if(Objects.equals(user.getMaLoai(),"2")){
                GiaoVien.add(user);
            }
        });

        Singleton.getInstant().getViewQuanLy().getTab_selected().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "Sinh viên")){
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(SinhVien));
            }else {
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(GiaoVien));
            }
        });

        Singleton.getInstant().getQuanLyTaiKhoan().getIsUpdate().addListener((observable, oldValue, newValue) -> {
            load(newValue);
        });

        Singleton.getInstant().getQuanLyTaiKhoan().getIsDelete().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(true)){
                NguoiDung.getRepository().delete(new NguoidungEntity(Singleton.getInstant().getQuanLyTaiKhoan().getAccountType().get(), Singleton.getInstant().getQuanLyTaiKhoan().getUsername().get(), Singleton.getInstant().getQuanLyTaiKhoan().getPassword().get()));
                if(Singleton.getInstant().getQuanLyTaiKhoan().getAccountType().get().equals("3")) {
                    SinhVien.removeIf(nguoidungEntity -> nguoidungEntity.getTenDangNhap().equals(Singleton.getInstant().getQuanLyTaiKhoan().getUsername().get()));
                    TableView_TaiKhoan.setItems(FXCollections.observableArrayList(SinhVien));
                }else {
                    GiaoVien.removeIf(nguoidungEntity -> nguoidungEntity.getTenDangNhap().equals(Singleton.getInstant().getQuanLyTaiKhoan().getUsername().get()));
                    TableView_TaiKhoan.setItems(FXCollections.observableArrayList(GiaoVien));
                }
            }
        });

        Singleton.getInstant().getQuanLyTaiKhoan().getIsCreate().addListener((observable, oldValue, newValue) -> {
            NguoidungEntity nguoiDung = new NguoidungEntity(Singleton.getInstant().getQuanLyTaiKhoan().getCreateAccountType().get(),
                    Singleton.getInstant().getQuanLyTaiKhoan().getCreate_username().get(),
                    Singleton.getInstant().getQuanLyTaiKhoan().getCreate_password().get());
            if(newValue.equals(true)){
                if(Singleton.getInstant().getQuanLyTaiKhoan().getCreateAccountType().get().equals("3")) {
                    SinhVien.add(nguoiDung);
                    TableView_TaiKhoan.setItems(FXCollections.observableArrayList(SinhVien));
                } else {
                    GiaoVien.add(nguoiDung);
                    TableView_TaiKhoan.setItems(FXCollections.observableArrayList(GiaoVien));
                }
                data.add(nguoiDung);
            }
        });
    }

    private void load(Boolean newValue) {
        if(newValue.equals(true)){
            NguoidungEntity nguoiDung = new NguoidungEntity(Singleton.getInstant().getQuanLyTaiKhoan().getAccountType().get(),
                    Singleton.getInstant().getQuanLyTaiKhoan().getUsername().get(),
                    Singleton.getInstant().getQuanLyTaiKhoan().getPassword().get());

            if(Singleton.getInstant().getQuanLyTaiKhoan().getAccountType().get().equals("3")) {
                SinhVien.removeIf(nguoidungEntity -> nguoidungEntity.getTenDangNhap().equals(Singleton.getInstant().getQuanLyTaiKhoan().getUsername().get()));
                SinhVien.add(nguoiDung);
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(SinhVien));
            }else {
                GiaoVien.removeIf(nguoidungEntity -> nguoidungEntity.getTenDangNhap().equals(Singleton.getInstant().getQuanLyTaiKhoan().getUsername().get()));
                GiaoVien.add(nguoiDung);
                TableView_TaiKhoan.setItems(FXCollections.observableArrayList(GiaoVien));
            }
        }
    }
}
