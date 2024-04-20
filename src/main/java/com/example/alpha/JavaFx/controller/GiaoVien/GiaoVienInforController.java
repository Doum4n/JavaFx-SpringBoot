package com.example.alpha.JavaFx.controller.GiaoVien;

import com.example.alpha.JavaFx.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class GiaoVienInforController implements Initializable {
    @FXML
    private Label Label_DiaChi;

    @FXML
    private Label Label_DienThoai;

    @FXML
    private Label Label_MaGV;

    @FXML
    private Label Label_TenGV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<GiaovienEntity> giaovienEntities = GiaoVien.getRepository().findAll();

        Singleton.getInstant().getDiemQuaTrinh().getMaGVSelected().addListener((observable, oldValue, newValue) -> {
            giaovienEntities.stream().filter(giaovienEntity -> Objects.equals(giaovienEntity.getMaGiaoVien(), newValue))
                    .findFirst().ifPresent(giaovienEntity -> {
                        Label_DiaChi.setText(giaovienEntity.getDiaChi());
                        Label_DienThoai.setText(giaovienEntity.getDienThoai());
                        Label_MaGV.setText(giaovienEntity.getMaGiaoVien());
                        Label_TenGV.setText(giaovienEntity.getTenGiaoVien());
                    });
        });
    }
}
