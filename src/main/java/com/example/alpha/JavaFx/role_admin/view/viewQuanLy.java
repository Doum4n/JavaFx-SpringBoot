package com.example.alpha.JavaFx.role_admin.view;

import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.Data;

@Data
public class viewQuanLy {
    private AnchorPane PhanLop;
    private AnchorPane QLGiaoVien;
    private AnchorPane PhanCong;
    private AnchorPane Diem;
    private AnchorPane EditSV;
    private AnchorPane TaiKhoan;
    private TableView<NguoidungEntity> tbView_TaiKhoan;
    private AnchorPane scrollPane;

    private final ObjectProperty<QuanLy> quanLyProperty;

    private StringProperty Tab_selected;


    private StringProperty Id;
    private StringProperty MaMH;

    private StringProperty MaSV;

    private ObjectProperty<AnchorPane> anchorPaneProperty;
    private ObjectProperty<Button> buttonProperty;

    public viewQuanLy() {
        this.quanLyProperty = new SimpleObjectProperty<>();
        this.Tab_selected = new SimpleStringProperty();
        this.Id = new SimpleStringProperty();
        this.MaMH = new SimpleStringProperty();

        this.anchorPaneProperty = new SimpleObjectProperty<>(new AnchorPane());
        this.buttonProperty = new SimpleObjectProperty<>(new Button("Xem"));
    }

    public AnchorPane getTaiKhoan(){
        try {
            if (TaiKhoan == null) {
                TaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/QuanLyTaiKhoan/TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaiKhoan;
    }

    public AnchorPane getDiem(){
        try {
            if (Diem == null) {
                Diem = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/NhapDiemThi/Diem.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Diem;
    }

    public AnchorPane getPCChamDiem() {
        try {
            if (scrollPane == null) {
                scrollPane = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/DiemQuaTrinh/QLDiemQuaTrinh.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return scrollPane;
    }

    public TableView<NguoidungEntity> getTbView_TaiKhoan() {
        try {
            if (tbView_TaiKhoan == null) {
                tbView_TaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/QuanLyTaiKhoan/TableView_TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbView_TaiKhoan;
    }
}
