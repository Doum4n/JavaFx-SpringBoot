package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemQuaTrinh.info;

import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.MonHoc.MonHoc;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LopMHInnforController implements Initializable {
    @FXML
    private Label Label_Lop;

    @FXML
    private Label Label_MaMH;

    @FXML
    private Label Label_TenMH;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton.getInstant().getDiemQuaTrinh().getMaMHSelected().addListener((observable, oldValue, newValue) -> {
            Label_MaMH.setText(newValue);
            Label_TenMH.setText(MonHoc.getRepository().getTenMH(newValue));
        });
        Singleton.getInstant().getDiemQuaTrinh().getLopSelected().addListener((observable, oldValue, newValue) -> {
            Label_Lop.setText(newValue);
        });
    }
}
