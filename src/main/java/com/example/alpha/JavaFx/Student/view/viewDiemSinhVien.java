package com.example.alpha.JavaFx.Student.view;

import com.example.alpha.Spring_boot.subject.DiemEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

public class viewDiemSinhVien {
    private TableView<DiemEntity> TableViewDiemSV;

    public TableView<DiemEntity> getTableViewDiemSV() {
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
