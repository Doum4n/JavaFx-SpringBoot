package com.example.alpha.JavaFx.Student.view;

import com.example.alpha.Spring_boot.subject.DiemThiEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

public class viewDiemSinhVien {
    private TableView<DiemThiEntity> TableViewDiemSV;

    public TableView<DiemThiEntity> getTableViewDiemSV() {
        try {
            if (TableViewDiemSV == null) {
                TableViewDiemSV = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/ThongKe/DiemSV.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableViewDiemSV;
    }
}
