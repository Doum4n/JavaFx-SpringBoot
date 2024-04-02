package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.CellGiaoVien;
import com.example.alpha.JavaFx.model.GiaoVien;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanCong;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import com.example.alpha.repository.GiaovienEntityRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class PCChamDiemController implements Initializable {
    @FXML
    private VBox VBox_GV;

    private StringProperty MaGV = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<GiaovienEntity> giaovienEntities = GiaoVien.getRepository().findAll();
        List<PhancongEntity> phanCongList = PhanCong.getRepository().findAll();

        for (int i=1;i<phanCongList.size();i+=1) {
            if(Objects.equals(Model.getInstant().getCellGiaoVien().getMaGV().get(), phanCongList.get(i).getMaGiaoVien()) && i+1!=phanCongList.size()) {
                continue;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/alpha/CellGiaoVien.fxml"));
            Pane pane;
            try {
                pane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for(int j=0;j<giaovienEntities.size();j++) {
                if(giaovienEntities.get(j).getMaGiaoVien().equals(phanCongList.get(i).getMaGiaoVien())) {
                    Model.getInstant().getCellGiaoVien().getMaGV().set(giaovienEntities.get(j).getMaGiaoVien());
                    Model.getInstant().getCellGiaoVien().getTenGV().set(giaovienEntities.get(j).getTenGiaoVien());
                    Model.getInstant().getCellGiaoVien().getMaMH().set(giaovienEntities.get(j).getMaMonHoc());
                }
            }

            double height = pane.getHeight();
            if(Model.getInstant().getViewQuanLy().getSlMH().get()>=2){
                for(int k=0;k<Model.getInstant().getViewQuanLy().getSlMH().get();k++) {
                    pane.setPrefHeight(height + 38);
                    pane.setMinHeight(height + 38);
                    pane.setMaxHeight(height + 38);
                    height += 38;
                }
                Model.getInstant().getViewQuanLy().getSlMH().set(0);
            }

            VBox_GV.getChildren().add(pane);
        }
    }
}