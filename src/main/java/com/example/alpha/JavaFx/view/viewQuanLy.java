package com.example.alpha.JavaFx.view;

import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.Data;

@Data
public class viewQuanLy {
    private AnchorPane PhanLop;
    private AnchorPane QLSinhVien;
    private AnchorPane QLGiaoVien;
    private AnchorPane PhanCong;
    private AnchorPane Diem;
    private AnchorPane EditSV;
    private AnchorPane TaiKhoan;
    private TableView<NguoidungEntity> tbView_TaiKhoan;

    private final ObjectProperty<QuanLy> quanLyProperty;

    private StringProperty Tab_selected;

    public viewQuanLy() {
        this.quanLyProperty = new SimpleObjectProperty<>();
        this.Tab_selected = new SimpleStringProperty();
    }

    public AnchorPane getQLSinhVien(){
        try {
            if (QLSinhVien == null) {
                QLSinhVien = new FXMLLoader(getClass().getResource("/com/example/alpha/SinhVien.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QLSinhVien;
    }

    public AnchorPane getQLGiaoVien(){
        try {
            if (QLGiaoVien == null) {
                QLGiaoVien = new FXMLLoader(getClass().getResource("/com/example/alpha/GiaoVien.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QLGiaoVien;
    }

    public AnchorPane getTaiKhoan(){
        try {
            if (TaiKhoan == null) {
                TaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaiKhoan;
    }

    public AnchorPane getDiem(){
        try {
            if (Diem == null) {
                Diem = new FXMLLoader(getClass().getResource("/com/example/alpha/Diem.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Diem;
    }

    public AnchorPane getPhanLop(){
        try {
            if (PhanLop == null) {
                PhanLop = new FXMLLoader(getClass().getResource("/com/example/alpha/PhanLop.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PhanLop;
    }

    public AnchorPane getPhanCong(){
        try {
            if (PhanCong == null) {
                PhanCong = new FXMLLoader(getClass().getResource("/com/example/alpha/PhanCong.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PhanCong;
    }

    public TableView<NguoidungEntity> getTbView_TaiKhoan() {
        try {
            if (tbView_TaiKhoan == null) {
                tbView_TaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/TableView_TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbView_TaiKhoan;
    }
}
