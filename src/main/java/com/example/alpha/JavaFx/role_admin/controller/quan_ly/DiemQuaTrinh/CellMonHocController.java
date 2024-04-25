package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemQuaTrinh;

import com.example.alpha.JavaFx.role_admin.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.role_admin.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
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
import org.springframework.util.Assert;

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
    List<GiaovienEntity> giaovienEntities = com.example.alpha.JavaFx.role_admin.model.GiaoVien.GiaoVien.getRepository().findAll();

    //Số lần xuất hiện của MaGV trong bảng phancong
    private final Map<String, Integer> SL = new HashMap<>();

    //Gán mỗi pane với khóa là mã giáo viên
    private final Map<String, Pane> GiaoVien = new HashMap<>();

    //Lọc dữ liệu
    private final Map<String, String> GV_hocky = new HashMap<>();
    private final Map<String, String> GV_namhoc = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label_MaMH.setText(Singleton.getInstant().getCellGiaoVien().getMaMH().get());

        //VBox tự động thay đổi chiều cao khi chèn thêm CellGiaoVien
        VBox_cellGV.setPrefHeight(Region.USE_COMPUTED_SIZE);

        loadCellGiaoVien();

        SL.forEach((s, integer) -> {
            if(integer>=2){
                Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().set(integer);
            }
        });

        Singleton.getInstant().getDiemQuaTrinh().getSearch_GV().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty() || !newValue.isBlank()) {
                GiaoVien.forEach((MaGV, pane) -> {
                    if (MaGV.equals(newValue)
                            && GV_hocky.get(MaGV).equals(Singleton.getInstant().getViewFactory().getHocky().get())
                            && GV_namhoc.get(MaGV).equals(Singleton.getInstant().getViewFactory().getNamHoc().get())) {
                        VBox_cellGV.getChildren().clear();
                        VBox_cellGV.getChildren().add(pane);
                    }
                });
            }else {
                loadCellGiaoVien();
            }
        });
    }

    private void loadCellGiaoVien(){
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

                Singleton.getInstant().getDiemQuaTrinh().getSearch_GV().addListener((observable, oldValue, newValue) -> {
                    if (pane.getParent()!=null) {
                        if(newValue.equals(((Label)pane.lookup("#Label_MaGV")).getText())){
                            Singleton.getInstant().getDiemQuaTrinh().getPane().set(((Label)pane.getParent().getParent().lookup("#Label_MaMH")).getText());
                        }
                    }
                });

                GiaoVien.put(phanCong.getMaGiaoVien(),pane);
                GV_hocky.put(phanCong.getMaGiaoVien(), phanCong.getMaHocKy());
                GV_namhoc.put(phanCong.getMaGiaoVien(), phanCong.getMaNamHoc());
            }
        }
    }
}
