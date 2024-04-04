package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.GiaoVien;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanCong;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class CellGiaoVienController implements Initializable {
    @FXML
    private Label Label_MaGV;

    @FXML
    private Label Label_MaMH;

    @FXML
    private Label Label_tenGV;

    @FXML
    private VBox VBox_LopHoc;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int i=0; //Số lần xuất hiện của MaGV trong bảng phân công
        List<PhancongEntity> phancongEntities = PhanCong.getRepository().findAll();
        Label_MaGV.setText(Model.getInstant().getCellGiaoVien().getMaGV().get());
        Label_tenGV.setText(Model.getInstant().getCellGiaoVien().getTenGV().get());
        Label_MaMH.setText(Model.getInstant().getCellGiaoVien().getMaMH().get());

        //lưu MaGV, MaMH để tìm kiếm
        Model.getInstant().getViewQuanLy().getId().set(Model.getInstant().getCellGiaoVien().getMaGV().get());
        Model.getInstant().getViewQuanLy().getMaMH().set(Model.getInstant().getCellGiaoVien().getMaMH().get());

        Map<String, Integer> SL = new HashMap<>();

        // Tải CellClass.fxml cho mỗi PhanCong khớp
        for (PhancongEntity phanCong : phancongEntities) {
            if (Objects.equals(phanCong.getMaGiaoVien(), Model.getInstant().getCellGiaoVien().getMaGV().get())) {
                Model.getInstant().getCellGiaoVien().getMaLop().set(phanCong.getMaLop());

                FXMLLoader loaderClass = new FXMLLoader();
                loaderClass.setLocation(getClass().getResource("/com/example/alpha/CellClass.fxml"));
                AnchorPane anchorPaneClass;
                try {
                    anchorPaneClass = loaderClass.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                VBox_LopHoc.getChildren().add(anchorPaneClass);
                SL.put(Model.getInstant().getCellGiaoVien().getMaGV().get(),++i);
            }
        }

        //Lấy số lần xuất hiện của MaGV trong bảng phân công
        SL.forEach((s, integer) -> {
            if(integer>=2){
                Model.getInstant().getViewQuanLy().getSlMH().set(integer);
            }
        });
    }
}
