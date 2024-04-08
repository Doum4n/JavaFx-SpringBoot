package com.example.alpha.JavaFx.view;

import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.Data;
import lombok.Getter;

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
    private AnchorPane scrollPane;

    private final ObjectProperty<QuanLy> quanLyProperty;

    private StringProperty Tab_selected;
    private IntegerProperty SlMH;

    private StringProperty Id;
    private StringProperty MaMH;

    private StringProperty MaSV;

    private StringProperty LopSelected;
    private StringProperty MaGVSelected;
    private StringProperty MaMHSelected;

    private ObjectProperty<AnchorPane> anchorPaneProperty;
    private ObjectProperty<Button> buttonProperty;

    private StringProperty SvSelected;

    public viewQuanLy() {
        this.quanLyProperty = new SimpleObjectProperty<>();
        this.Tab_selected = new SimpleStringProperty();
        this.SlMH = new SimpleIntegerProperty(1);
        this.Id = new SimpleStringProperty();
        this.MaMH = new SimpleStringProperty();

        this.LopSelected = new SimpleStringProperty();
        this.MaGVSelected = new SimpleStringProperty();
        this.MaMHSelected = new SimpleStringProperty();

        this.SvSelected = new SimpleStringProperty();

        this.anchorPaneProperty = new SimpleObjectProperty<>(new AnchorPane());
        this.buttonProperty = new SimpleObjectProperty<>(new Button("Xem"));
    }

    public AnchorPane getQLSinhVien(){
        try {
            if (QLSinhVien == null) {
                QLSinhVien = new FXMLLoader(getClass().getResource("/com/example/alpha/DiemSinhVien/SinhVien.fxml")).load();
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
                TaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/QuanLyTaiKhoan/TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaiKhoan;
    }

    public AnchorPane getDiem(){
        try {
            if (Diem == null) {
                Diem = new FXMLLoader(getClass().getResource("/com/example/alpha/NhapDiemThi/Diem.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Diem;
    }

    public AnchorPane getPCChamDiem() {
        try {
            if (scrollPane == null) {
                scrollPane = new FXMLLoader(getClass().getResource("/com/example/alpha/DiemQuaTrinh/PhanCongChamDiem.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return scrollPane;
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
                tbView_TaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/QuanLyTaiKhoan/TableView_TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbView_TaiKhoan;
    }
}
