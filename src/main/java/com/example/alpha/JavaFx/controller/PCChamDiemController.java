package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.CellGiaoVien;
import com.example.alpha.JavaFx.model.GiaoVien;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanCong;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class PCChamDiemController implements Initializable {
    @FXML
    private VBox VBox_GV;

    @FXML
    private ScrollPane ScrollPane_Main;

    @FXML
    private TextField textField_Search;

    private SortedList<Pane> panes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<GiaovienEntity> giaovienEntities = GiaoVien.getRepository().findAll();
        List<PhancongEntity> phanCongList = PhanCong.getRepository().findAll();
        ScrollPane_Main.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //ScrollPane_Main.setFitToHeight(true);

        Map<Pane,String> MaGV = new HashMap<>();
        Map<Pane,String> MaMH = new HashMap<>();

        // Tải CellGiaoVien cho mỗi Giáo viên được phân công
        for (int i=1;i<phanCongList.size();i++) {
            if(Objects.equals(Model.getInstant().getCellGiaoVien().getMaGV().get(), phanCongList.get(i).getMaGiaoVien()) && i+1!=phanCongList.size()) {
                continue;
            }
            System.out.println("GV "+i+": "+phanCongList.get(i).getMaGiaoVien());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/alpha/CellGiaoVien.fxml"));
            Pane pane;
            try {
                pane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //So sánh với bảng giáo viên
            for (GiaovienEntity giaovienEntity : giaovienEntities) {
                if (phanCongList.get(i).getMaGiaoVien().equals(giaovienEntity.getMaGiaoVien())) {
                    Model.getInstant().getCellGiaoVien().getMaGV().set(giaovienEntity.getMaGiaoVien());
                    Model.getInstant().getCellGiaoVien().getTenGV().set(giaovienEntity.getTenGiaoVien());
                    Model.getInstant().getCellGiaoVien().getMaMH().set(phanCongList.get(i).getMaMonHoc());
                }
            }

            //Tính độ cao của CellGiaovien
            double height = pane.getHeight();
            if (Model.getInstant().getViewQuanLy().getSlMH().get() >= 2) {
                for (int k = 0; k < Model.getInstant().getViewQuanLy().getSlMH().get(); k++) {
                    pane.setPrefHeight(height + 33);
                    pane.setMinHeight(height + 33);
                    pane.setMaxHeight(height + 33);
                    height += 33;
                }
                Model.getInstant().getViewQuanLy().getSlMH().set(1);
            }

            VBox_GV.getChildren().add(pane);

            //Đưa vào danh sách để gắn mỗi Pane là MaGV, và MaMH
            MaGV.put(pane, Model.getInstant().getViewQuanLy().getId().get());
            MaMH.put(pane, Model.getInstant().getViewQuanLy().getMaMH().get());

            panes = new SortedList<>(FXCollections.observableArrayList(MaGV.keySet()));
            //VBox tự động thay đổi chiều cao cho phù hợp với đối tượng vừa thêm vào
            VBox_GV.setPrefHeight(Region.USE_COMPUTED_SIZE);
        }

        //Tìm kiếm dựa trên MaGV và MaMH
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                MaGV.forEach((pane, s) -> {
                    if (newValue.equals(s)) {
                        VBox_GV.getChildren().clear();
                        VBox_GV.getChildren().add(pane);
                    }
                });
                MaMH.forEach((pane, s) -> {
                    if(newValue.equals(s)){
                        VBox_GV.getChildren().clear();
                        VBox_GV.getChildren().add(pane);
                    }
                });
            }else{
                VBox_GV.getChildren().clear();
                VBox_GV.getChildren().addAll(panes);
            }
        });
        System.out.println(MaGV.keySet());
    }
}