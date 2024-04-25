package com.example.alpha.JavaFx.role_admin.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

@Getter
public class viewThongKe {
    private final ObjectProperty<ThongKe> thongKeProperty;

    private AnchorPane TK_DiemSV_MH;
    private AnchorPane TK_DiemLop_MonHoc;
    private AnchorPane TK_DiemLop_HocKy;
    private AnchorPane TK_DiemLop_NamHoc;

    public viewThongKe() {
        thongKeProperty = new SimpleObjectProperty<>();
    }

    public AnchorPane getTK_DiemSV_MH(){
        try {
            if (TK_DiemSV_MH == null) {
                TK_DiemSV_MH = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/ThongKe/SinhVien.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TK_DiemSV_MH;
    }

    public AnchorPane getTK_DiemLop_MonHoc(){
        try {
            if (TK_DiemLop_MonHoc == null) {
                TK_DiemLop_MonHoc = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/ThongKe/TK_DiemLop_MonHoc.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TK_DiemLop_MonHoc;
    }

    public AnchorPane getTK_DiemLop_HocKy(){
        try {
            if (TK_DiemLop_HocKy == null) {
                TK_DiemLop_HocKy = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/ThongKe/TK_DiemLop_HocKy.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TK_DiemLop_HocKy;
    }

    public AnchorPane getTK_DiemLop_NamHoc() {
        try {
            if (TK_DiemLop_NamHoc == null) {
                TK_DiemLop_NamHoc = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/ThongKe/TK_DiemLop_NamHoc.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TK_DiemLop_NamHoc;
    }
}
