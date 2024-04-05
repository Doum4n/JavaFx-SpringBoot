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
import org.springframework.jmx.export.metadata.ManagedOperation;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class PCChamDiemController implements Initializable{
    @FXML
    private VBox VBox_GV;

    @FXML
    private TableColumn<?, ?> Column_DiemQT;

    @FXML
    private TableColumn<DiemQTEntity, String> Column_MaSV;

    @FXML
    private TableColumn<DiemQTEntity, String> Column_TenSV;

    @FXML
    private TableView<DiemQTEntity> TableView_Diem;

    @FXML
    private Label Label_DiaChi;

    @FXML
    private Label Label_DienThoai;

    @FXML
    private Label Label_MaGV;

    @FXML
    private Label Label_TenGV;

    @FXML
    private ScrollPane ScrollPane_Main;

    @FXML
    private TextField textField_Search;

    private SortedList<Pane> panes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        List<GiaovienEntity> giaovienEntities = GiaoVien.getRepository().findAll();
        List<PhancongEntity> phanCongList = PhanCong.getRepository().findAll();
        ScrollPane_Main.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //VBox tự động thay đổi chiều cao cho phù hợp với đối tượng vừa thêm vào
        VBox_GV.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //ScrollPane_Main.setFitToHeight(true);

        Map<Pane,String> MaGV = new HashMap<>();
        Map<Pane,String> MaMH = new HashMap<>();

        // Tải CellGiaoVien cho mỗi Giáo viên được phân công
        for (int i=1; i<phanCongList.size(); i++) {
            // Lấy MaGV xuất hiện lần đầu trong MaGV của CellGiaoVien
            if(!Model.getInstant().getCellGiaoVien().getMaGV().get().contains(phanCongList.get(i).getMaGiaoVien())) {
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


                //Thêm vào VBox_GV
                VBox_GV.getChildren().add(pane);

                //Đưa vào danh sách để gắn mỗi Pane là MaGV, và MaMH (tìm kiếm)
                MaGV.put(pane, Model.getInstant().getViewQuanLy().getId().get());
                MaMH.put(pane, Model.getInstant().getViewQuanLy().getMaMH().get());

                panes = new SortedList<>(FXCollections.observableArrayList(MaGV.keySet()));
            }
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
                /*MaMH.forEach((pane, s) -> {
                    if(newValue.equals(s)){
                        VBox_GV.getChildren().clear();
                        VBox_GV.getChildren().add(pane);
                    }
                });*/
            }else{
                VBox_GV.getChildren().clear();
            }
        });
    }
}