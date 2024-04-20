package com.example.alpha.JavaFx.Student;

import com.example.alpha.JavaFx.model.Diem.DiemSV_CaNam;
import com.example.alpha.JavaFx.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DiemTKSinhVienController implements Initializable {
    @FXML
    private Label Label_DiemTK;

    @FXML
    private Label Label_HocKy;

    @FXML
    private Label Label_TongTC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label_DiemTK.setText(DiemSV_HocKy.getRepository().getDiemTK(Singleton.getInstant().getDiemSinnVienStudent().getMaSV().get(), Singleton.getInstant().getDiemSinnVienStudent().getHocKy().get(), Singleton.getInstant().getDiemSinnVienStudent().getNamHoc().get()).toString());
        Label_TongTC.setText(DiemSV_HocKy.getRepository().getTongTC(Singleton.getInstant().getDiemSinnVienStudent().getMaSV().get(), Singleton.getInstant().getDiemSinnVienStudent().getHocKy().get(), Singleton.getInstant().getDiemSinnVienStudent().getNamHoc().get()).toString());
        Label_HocKy.setText(Singleton.getInstant().getDiemSinnVienStudent().getHocKy().get());
    }
}
