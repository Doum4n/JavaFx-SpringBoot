package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemQuaTrinh;

import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.GiaoVien.PhanCong;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class CellGiaoVienController implements Initializable {
    @FXML
    private Label Label_MaGV;

    @FXML
    private Label Label_tenGV;

    @FXML
    private VBox VBox_LopHoc;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int i=0; //Số lần xuất hiện của MaGV trong bảng phân công
        List<PhancongEntity> phancongEntities = PhanCong.getRepository().findAll();
        Label_MaGV.setText(Singleton.getInstant().getCellGiaoVien().getMaGV().get());
        Label_tenGV.setText(Singleton.getInstant().getCellGiaoVien().getTenGV().get());

        //lưu MaGV, MaMH để tìm kiếm
        Singleton.getInstant().getViewQuanLy().getId().set(Singleton.getInstant().getCellGiaoVien().getMaGV().get());
        Singleton.getInstant().getViewQuanLy().getMaMH().set(Singleton.getInstant().getCellGiaoVien().getMaMH().get());

        Map<String, Integer> SL = new HashMap<>();

        // Tải CellClass.fxml cho mỗi PhanCong khớp
        for (PhancongEntity phanCong : phancongEntities) {
            if (Objects.equals(phanCong.getMaGiaoVien(), Singleton.getInstant().getCellGiaoVien().getMaGV().get())
                    && Objects.equals(phanCong.getMaMonHoc(), Singleton.getInstant().getCellGiaoVien().getMaMH().get())
                    && Objects.equals(phanCong.getMaNamHoc(), Singleton.getInstant().getViewFactory().getNamHoc().get())
                    && Objects.equals(phanCong.getMaHocKy(), Singleton.getInstant().getViewFactory().getHocky().get()))
            {

                Singleton.getInstant().getCellGiaoVien().getMaLop().set(phanCong.getMaLop());

                FXMLLoader loaderClass = new FXMLLoader();
                loaderClass.setLocation(getClass().getResource("/com/example/alpha/fxml/role_admin/DiemQuaTrinh/CellClass.fxml"));
                AnchorPane anchorPaneClass;
                try {
                    anchorPaneClass = loaderClass.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                VBox_LopHoc.getChildren().add(anchorPaneClass);

                SL.put(Singleton.getInstant().getCellGiaoVien().getMaMH().get(),++i);
            }
        }

        //Lấy số lần xuất hiện của MaGV trong bảng phân công
        SL.forEach((s, integer) -> {
            if(integer>=2){
                Singleton.getInstant().getCellGiaoVien().getSlLop().set(integer);
            }
        });
    }
}
