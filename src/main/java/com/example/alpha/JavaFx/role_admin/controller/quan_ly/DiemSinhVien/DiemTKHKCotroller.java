package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemSinhVien;

import com.example.alpha.JavaFx.role_admin.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DiemTKHKCotroller implements Initializable {
    @FXML
    private Label Label_DiemTBHK;

    @FXML
    private Label Label_SoTC;

    @FXML
    private Label Label_XepLoai;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {
            Label_DiemTBHK.setText(String.valueOf(Singleton.getInstant().getDiemSinhVien().getDiemTK().get()));
            Label_SoTC.setText(String.valueOf(Singleton.getInstant().getDiemSinhVien().getSoTC().get()));
            double diem = Double.parseDouble(Label_DiemTBHK.getText());
            if(!Label_DiemTBHK.getText().equals("0.0") && !Label_SoTC.getText().equals("0.0")) {
                if (diem >= 9) {
                    Label_XepLoai.setText("Xuất sắc");
                } else if (diem >= 8) {
                    Label_XepLoai.setText("Giỏi");
                } else if (diem >= 7) {
                    Label_XepLoai.setText("Khá");
                } else if (diem >= 6) {
                    Label_XepLoai.setText("Trung bình khá");
                } else if (diem >= 5) {
                    Label_XepLoai.setText("Trung bình");
                } else if (diem >= 4) {
                    Label_XepLoai.setText("Yếu");
                } else {
                    Label_XepLoai.setText("Kém");
                }
            }else {
                Label_XepLoai.setText("");
            }
        });
    }
}
