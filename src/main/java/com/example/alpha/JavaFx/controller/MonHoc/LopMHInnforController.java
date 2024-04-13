package com.example.alpha.JavaFx.controller.MonHoc;

import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;

import java.net.URL;
import java.sql.SQLOutput;
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
        Model.getInstant().getDiemQuaTrinh().getMaMHSelected().addListener((observable, oldValue, newValue) -> {
            Label_MaMH.setText(newValue);
            Label_TenMH.setText(MonHoc.getRepository().getTenMH(newValue));
        });
        Model.getInstant().getDiemQuaTrinh().getLopSelected().addListener((observable, oldValue, newValue) -> {
            Label_Lop.setText(newValue);
        });
    }
}
