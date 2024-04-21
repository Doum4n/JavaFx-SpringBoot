package com.example.alpha.JavaFx.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

public class viewDanhGia {
    private AnchorPane DSHocBong;
    private AnchorPane DSKhoaLuan;

    @Getter
    private final ObjectProperty<DanhGia> DanhGiaProperty;

    public viewDanhGia() {
        this.DanhGiaProperty = new SimpleObjectProperty<>();
    }

    public AnchorPane getDSHocBong() {
        try {
            if (DSHocBong == null) {
                DSHocBong = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/DanhGia/DSHocBong.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DSHocBong;
    }

    public AnchorPane getDSKhoaLuan() {;
        try {
            if (DSKhoaLuan == null) {
                DSKhoaLuan = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/DanhGia/DSKhoaLuan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DSKhoaLuan;
    }
}
