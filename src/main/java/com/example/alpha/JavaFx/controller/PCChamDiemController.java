package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.*;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import com.example.alpha.Spring_boot.subject.DiemQTPK;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.hibernate.sql.results.NoMoreOutputsException;
import org.springframework.boot.Banner;
import org.springframework.jmx.export.metadata.ManagedOperation;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

@Controller
public class PCChamDiemController implements Initializable{
    @FXML
    private VBox VBox_GV;
    @FXML
    private ScrollPane ScrollPane_Main;
    @FXML
    private TextField textField_Search;
    private List<GiaovienEntity> giaovienEntities;
    private List<PhancongEntity> phanCongList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        giaovienEntities = GiaoVien.getRepository().findAll();
        phanCongList = PhanCong.getRepository().findAll();
        ScrollPane_Main.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //VBox tự động thay đổi chiều cao cho phù hợp với đối tượng vừa thêm vào
        VBox_GV.setPrefHeight(Region.USE_COMPUTED_SIZE);

        Map<String, Pane> MaGV = new HashMap<>();
        Map<String, Pane> MaMH = new HashMap<>();

        addData(MaGV,MaMH);

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            addData(MaGV,MaMH);
        });

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            addData(MaGV,MaMH);
        });

        //Tìm kiếm dựa trên MaGV và MaMH
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                phanCongList.stream().filter(phancongEntity -> phancongEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get())
                        && phancongEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()))
                        .forEach(phancongEntity -> MaGV.forEach((s, pane) -> {
                            if (newValue.equals(s) && s.equals(phancongEntity.getMaGiaoVien())) {
                                VBox_GV.getChildren().clear();
                                VBox_GV.getChildren().add(pane);
                            }
                        }));
                /*MaMH.forEach((pane, s) -> {
                    if(newValue.equals(s)){
                        VBox_GV.getChildren().clear();
                        VBox_GV.getChildren().add(pane);
                    }
                });*/
            }else{
                addData(MaGV,MaMH);
            }
        });
    }

    private void addData(Map<String,Pane> MaGV, Map<String,Pane> MaMH){
        /*Xóa dữ liệu cũ trước khi cập nhật*/
        VBox_GV.getChildren().clear();

        // Tải CellGiaoVien cho mỗi Giáo viên được phân công
        for (int i=0; i<phanCongList.size(); i++) {
            // Lấy MaGV xuất hiện lần đầu trong MaGV của CellGiaoVien
            if(!Model.getInstant().getCellGiaoVien().getMaGV().get().contains(phanCongList.get(i).getMaGiaoVien())
                    && Objects.equals(Model.getInstant().getViewFactory().getHocky().get(),phanCongList.get(i).getMaHocKy())
                    && Objects.equals(phanCongList.get(i).getMaNamHoc(), Model.getInstant().getViewFactory().getNamHoc().get())) {

                //Cập nhật thông tin trước khi tải CellGiaoVien
                // Thiết lập MaGV cho CellGiaoVien trước vì Model.getInstant().getCellGiaoVien().getMaGV().get = ""
                Model.getInstant().getCellGiaoVien().getMaGV().set(phanCongList.get(i).getMaGiaoVien());
                for (GiaovienEntity giaovienEntity : giaovienEntities) {
                    if (phanCongList.get(i).getMaGiaoVien().equals(giaovienEntity.getMaGiaoVien())) {
                        Model.getInstant().getCellGiaoVien().getMaMH().set(phanCongList.get(i).getMaMonHoc());
                        Model.getInstant().getCellGiaoVien().getTenGV().set(giaovienEntity.getTenGiaoVien());
                        Model.getInstant().getCellGiaoVien().getDiaChi().set(giaovienEntity.getDiaChi());
                        Model.getInstant().getCellGiaoVien().getSDT().set(giaovienEntity.getDienThoai());
                    }
                }

                //Tải CellGiaoVien
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/alpha/CellGiaoVien.fxml"));
                Pane pane;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
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

                Label MaGVn = (Label) pane.lookup("#Label_MaGV");
                //Thêm vào VBox_GV
                VBox_GV.getChildren().add(pane);

                //Đưa vào danh sách để gắn mỗi Pane là MaGV, và MaMH (tìm kiếm)
                MaGV.put(MaGVn.getText(), pane);
                MaMH.put(MaGVn.getText(), pane);
            }
        }
        /*Reset lại MaGV khi thay đổi năm, học kỳ*/
        Model.getInstant().getCellGiaoVien().getMaGV().set("");
    }
}