package com.example.alpha.JavaFx.controller.DiemQuaTrinh;

import com.example.alpha.JavaFx.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CellMonHocController implements Initializable {

    @FXML
    private Label Label_MaMH;

    @FXML
    private VBox VBox_cellGV;

    @FXML
    private AnchorPane AnchorPane_main;

    List<PhancongEntity> phancongEntities = PhanCong.getRepository().findAll();
    List<GiaovienEntity> giaovienEntities = GiaoVien.getRepository().findAll();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label_MaMH.setText(Singleton.getInstant().getCellGiaoVien().getMaMH().get());

        //VBox tự động thay đổi chiều cao khi chèn thêm CellGiaoVien
        VBox_cellGV.setPrefHeight(Region.USE_COMPUTED_SIZE);

        Map<String, Integer> SL = new HashMap<>();

        Map<String, Pane> MonHoc = new HashMap<>();

        loadCellGiaoVien(SL, MonHoc);

        //Lấy số lần xuất hiện của MaMH trong bảng phân công
        SL.forEach((s, integer) -> {
            if(integer>=2){
                Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().set(integer);
            }
        });

//        Singleton.getInstant().getDiemQuaTrinh().getSearch_GV().addListener((observable, oldValue, newValue) -> {
//            if(!newValue.isEmpty() || !newValue.isBlank()) {
//                MonHoc.forEach((MaGV, pane) -> {
//                    if (MaGV.equals(newValue)) {
//                        VBox_cellGV.getChildren().clear();
//                        VBox_cellGV.getChildren().add(pane);
//                    }
//                });
//            }else {
//                loadCellGiaoVien(SL, MonHoc);
//            }
//        });
    }

    private void loadCellGiaoVien(Map<String, Integer> SL, Map<String, Pane> MonHoc){
        int i=0; //Số lượng giáo viện giảng dạy cùng một môn
        // Tải CellGiaoVien.fxml cho mỗi PhanCong khớp
        for (PhancongEntity phanCong : phancongEntities) {
            if (Objects.equals(phanCong.getMaMonHoc(), Singleton.getInstant().getCellGiaoVien().getMaMH().get())
                    && Objects.equals(phanCong.getMaNamHoc(), Singleton.getInstant().getViewFactory().getNamHoc().get())
                    && Objects.equals(phanCong.getMaHocKy(), Singleton.getInstant().getViewFactory().getHocky().get())) {

                Singleton.getInstant().getCellGiaoVien().getMaGV().set(phanCong.getMaGiaoVien());

                for (GiaovienEntity giaovienEntity : giaovienEntities) {
                    if (phanCong.getMaGiaoVien().equals(giaovienEntity.getMaGiaoVien())) {
                        Singleton.getInstant().getCellGiaoVien().getTenGV().set(giaovienEntity.getTenGiaoVien());
                    }
                }

                FXMLLoader loaderClass = new FXMLLoader();
                loaderClass.setLocation(getClass().getResource("/com/example/alpha/fxml/role_admin/DiemQuaTrinh/CellGiaoVien.fxml"));
                Pane pane;
                try {
                    pane = loaderClass.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                VBox_cellGV.getChildren().add(pane);
                SL.put(Singleton.getInstant().getCellGiaoVien().getMaMH().get(),++i);

//                System.out.println(((Label)pane.lookup("#Label_MaGV")).getText());

                Singleton.getInstant().getDiemQuaTrinh().getSearch_GV().addListener((observable, oldValue, newValue) -> {
//                    System.out.println(newValue+":"+(((Label)pane.lookup("#Label_MaGV")).getText()));
                    if(newValue.equals(((Label)pane.lookup("#Label_MaGV")).getText())){
                        System.out.println(pane.getParent().getParent().lookup("#Label_MaMH"));
                        Singleton.getInstant().getDiemQuaTrinh().getPane().set(((Label)pane.getParent().getParent().lookup("#Label_MaMH")).getText());
                    }
                });

                MonHoc.put(phanCong.getMaGiaoVien(),pane);
            }
        }
    }
}
