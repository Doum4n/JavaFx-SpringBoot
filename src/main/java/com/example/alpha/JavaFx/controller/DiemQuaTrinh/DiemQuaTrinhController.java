package com.example.alpha.JavaFx.controller.DiemQuaTrinh;

import com.example.alpha.JavaFx.model.*;
import com.example.alpha.JavaFx.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class DiemQuaTrinhController implements Initializable{
    @FXML
    private VBox VBox_MonHoc;
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
        VBox_MonHoc.setPrefHeight(Region.USE_COMPUTED_SIZE);

        Map<String, Pane> MaGV = new HashMap<>();
        Map<String, Pane> MaMH = new HashMap<>();

        addData(MaGV,MaMH);
        addListenerSearchMaMonHoc(MaGV,MaMH);
        addListenerSearchMaSV();

        //Khi năm, học kỳ thay đổi
        Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            addData(MaGV,MaMH);
        });
        Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            addData(MaGV,MaMH);
        });

        //Hiển thi điểm khi nhấn vào ô bảng DiemQT
        Singleton.getInstant().getDiemQuaTrinh().getDiem().addListener((observable, oldValue, newValue) -> {
            TextField_DiemQT.setText(newValue);
        });

        Button_Update.setOnAction(event -> {
            KqMonHoc_SV.getRepository().updateDiem(Singleton.getInstant().getDiemQuaTrinh().getMaSV().get(),
                    Singleton.getInstant().getDiemQuaTrinh().getMaMHSelected().get(),
                    Float.valueOf(TextField_DiemQT.getText()));
            Singleton.getInstant().getDiemQuaTrinh().getDiem().set(TextField_DiemQT.getText());
            Singleton.getInstant().getDiemQuaTrinh().getIsUpdate().set(true);
            Singleton.getInstant().getDiemQuaTrinh().getIsUpdate().set(false);
        });
    }


    private void addData(Map<String,Pane> MaGV, Map<String, Pane>MaMH){
        /*Xóa dữ liệu cũ trước khi cập nhật*/
        VBox_MonHoc.getChildren().clear();

        List<String> MonHoc = new ArrayList<>(Collections.emptyList());

        List<Pane> panes = new ArrayList<>();
        // Tải CellMonHoc, tương ứng với mỗi CellMonHoc là nhưng giáo viên được phân công dạy một đó
        for (PhancongEntity phancongEntity : phanCongList) {
            // Lấy MaMH xuất hiện lần đầu trong MaGV của CellGiaoVien
            if (!MonHoc.contains(phancongEntity.getMaMonHoc())
                    && Objects.equals(Singleton.getInstant().getViewFactory().getHocky().get(), phancongEntity.getMaHocKy())
                    && Objects.equals(phancongEntity.getMaNamHoc(), Singleton.getInstant().getViewFactory().getNamHoc().get())) {

                //Cập nhật thông tin trước khi tải CellMonHoc
                MonHoc.add(Singleton.getInstant().getCellGiaoVien().getMaMH().get());
                Singleton.getInstant().getCellGiaoVien().getMaMH().set(phancongEntity.getMaMonHoc());

                //Tải CellMonHoc
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/alpha/fxml/role_admin/DiemQuaTrinh/CellMonHoc.fxml"));
                AnchorPane pane;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //Tính độ cao của CellMonhoc
                double height = pane.getHeight();
                if (Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().get() >= 2) {
                    for (int k = 0; k < Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().get(); k++) {
                        pane.setPrefHeight(height + 48);
                        pane.setMinHeight(height + 48);
                        pane.setMaxHeight(height + 48);
                        height += 48;
                    }
                    Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().set(1);
                }

                Label MaMonHoc = (Label) pane.lookup("#Label_MaMH");
                //Thêm vào VBox_GV
                VBox_MonHoc.getChildren().add(pane);

                //Đưa vào danh sách để gắn mỗi Pane là MaGV, và MaMH (tìm kiếm)
                MaMH.put(Singleton.getInstant().getCellGiaoVien().getMaMH().get(), pane);

                MaGV.put(Singleton.getInstant().getCellGiaoVien().getMaGV().get(), pane);

//                System.out.println(pane.getChildren().get(2).lookup("#Label_MaGV"));
            }
        }
        /*Reset lại MaGV khi thay đổi năm, học kỳ*/
        Singleton.getInstant().getCellGiaoVien().getMaGV().set("");
        Singleton.getInstant().getCellGiaoVien().getMaMH().set("");
    }

    private void addListenerSearchMaMonHoc(Map<String,Pane> MaGV, Map<String,Pane> MaMH){
        //Tìm kiếm dựa trên MaGV và MaMH
        textField_SearchMaGV.textProperty().addListener((observable, oldValue, newValue) -> {
            VBox_MonHoc.getChildren().clear();
            Singleton.getInstant().getDiemQuaTrinh().getSearch_GV().set(newValue);
            if(newValue.isBlank() || newValue.isEmpty())
                addData(MaGV, MaMH);
        });

        Singleton.getInstant().getDiemQuaTrinh().getPane().addListener((observable, oldValue, newValue) -> {
            MaMH.forEach((MonHoc, pane) -> {
                        if(Singleton.getInstant().getDiemQuaTrinh().getPane().get()!=null) {
                            System.out.println(VBox_MonHoc.getChildren());
                            if (newValue.equals(MonHoc) && !VBox_MonHoc.getChildren().contains(pane)) {
                                VBox_MonHoc.getChildren().add(pane);
                            }
                        }
                });
        });
    }

    private void addListenerSearchMaSV(){
        TextField_SearchMaSV.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!TextField_SearchMaSV.getText().isBlank()){
                Singleton.getInstant().getDiemQuaTrinh().getSearch_SV().set(newValue);
            }else {
                Singleton.getInstant().getDiemQuaTrinh().getSearch_SV().set("");
            }
        });
    }
}