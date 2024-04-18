package com.example.alpha.JavaFx.controller.GiaoVien;

import com.example.alpha.JavaFx.model.*;
import com.example.alpha.JavaFx.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class PCChamDiemController implements Initializable{
    @FXML
    private VBox VBox_GV;
    @FXML
    private ScrollPane ScrollPane_Main;
    @FXML
    private TextField textField_SearchMaGV;
    @FXML
    private TextField TextField_DiemQT;
    @FXML
    private TextField TextField_SearchMaSV;
    @FXML
    private Button Button_Update;

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
        addListenerSearchMaGV(MaGV,MaMH);
        addListenerSearchMaSV();

        //Khi năm, học kỳ thay đổi
        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            addData(MaGV,MaMH);
        });
        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            addData(MaGV,MaMH);
        });

        //Hiển thi điểm khi nhấn vào ô bảng DiemQT
        Model.getInstant().getDiemQuaTrinh().getDiem().addListener((observable, oldValue, newValue) -> {
            TextField_DiemQT.setText(newValue);
        });

        Button_Update.setOnAction(event -> {
            KqMonHoc_SV.getRepository().updateDiem(Model.getInstant().getDiemQuaTrinh().getMaSV().get(),
                    Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get(),
                    Float.valueOf(TextField_DiemQT.getText()));
            Model.getInstant().getDiemQuaTrinh().getDiem().set(TextField_DiemQT.getText());
            Model.getInstant().getDiemQuaTrinh().getIsUpdate().set(true);
            Model.getInstant().getDiemQuaTrinh().getIsUpdate().set(false);
        });
    }


    private void addData(Map<String,Pane> MaGV, Map<String,Pane> MaMH){
        /*Xóa dữ liệu cũ trước khi cập nhật*/
        VBox_GV.getChildren().clear();

        // Tải CellGiaoVien cho mỗi Giáo viên được phân công
        for (PhancongEntity phancongEntity : phanCongList) {
            // Lấy MaGV xuất hiện lần đầu trong MaGV của CellGiaoVien
            if (!Model.getInstant().getCellGiaoVien().getMaGV().get().contains(phancongEntity.getMaGiaoVien())
                    && !Model.getInstant().getCellGiaoVien().getMaMH().get().contains(phancongEntity.getMaMonHoc())
                    && Objects.equals(Model.getInstant().getViewFactory().getHocky().get(), phancongEntity.getMaHocKy())
                    && Objects.equals(phancongEntity.getMaNamHoc(), Model.getInstant().getViewFactory().getNamHoc().get())) {

                //Cập nhật thông tin trước khi tải CellGiaoVien
                // Thiết lập MaGV cho CellGiaoVien trước vì Model.getInstant().getCellGiaoVien().getMaGV().get = ""
                Model.getInstant().getCellGiaoVien().getMaGV().set(phancongEntity.getMaGiaoVien());
                Model.getInstant().getCellGiaoVien().getMaMH().set(phancongEntity.getMaMonHoc());
                for (GiaovienEntity giaovienEntity : giaovienEntities) {
                    if (phancongEntity.getMaGiaoVien().equals(giaovienEntity.getMaGiaoVien())) {
                        Model.getInstant().getCellGiaoVien().getMaMH().set(phancongEntity.getMaMonHoc());
                        Model.getInstant().getCellGiaoVien().getTenGV().set(giaovienEntity.getTenGiaoVien());
                        Model.getInstant().getCellGiaoVien().getDiaChi().set(giaovienEntity.getDiaChi());
                        Model.getInstant().getCellGiaoVien().getSDT().set(giaovienEntity.getDienThoai());
                    }
                }

                //Tải CellGiaoVien
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/alpha/DiemQuaTrinh/CellGiaoVien.fxml"));
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
        Model.getInstant().getCellGiaoVien().getMaMH().set("");
    }

    private void addListenerSearchMaGV(Map<String,Pane> MaGV, Map<String,Pane> MaMH){
        //Tìm kiếm dựa trên MaGV và MaMH
        textField_SearchMaGV.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                phanCongList.stream().filter(phancongEntity -> phancongEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get())
                                && phancongEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()))
                        .forEach(phancongEntity -> MaGV.forEach((s, pane) -> {
                            if (newValue.equals(s) && s.equals(phancongEntity.getMaGiaoVien())) {
                                VBox_GV.getChildren().clear();
                                VBox_GV.getChildren().add(pane);
                            }
                        }));
            }else{
                addData(MaGV,MaMH);
            }
        });
    }

    private void addListenerSearchMaSV(){
        TextField_SearchMaSV.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!TextField_SearchMaSV.getText().isBlank()){
                Model.getInstant().getDiemQuaTrinh().getSearch().set(newValue);
            }else {
                Model.getInstant().getDiemQuaTrinh().getSearch().set("");
            }
        });
    }
}