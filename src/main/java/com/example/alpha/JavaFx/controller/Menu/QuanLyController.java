package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.model.SinhVien.DKHocPhan;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.view.QuanLy;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinnhVienHocKy;
import com.example.alpha.Spring_boot.student.DKHocPhanEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

@Controller
public class QuanLyController implements Initializable {
    @FXML
    private Button button_Diem;

    @FXML
    private Button button_KetQua;

    @FXML
    private Button button_PhanCong;

    @FXML
    private Button button_TaiKhoan;

    @FXML
    private Button button_QLSinhVien;

    @FXML
    private Button Button_TinhDiemTB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_QLSinhVien.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.QLSinhVien));
        button_Diem.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.DiemThi));
//        button_PhanLop.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.PhanLop));
//        button_QLGiaoVien.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.QLGiaoVien));
        button_PhanCong.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.DiemQT));
        button_TaiKhoan.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.TaiKhoan));
//        button_PhanCong.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.PhanCong));
        Button_TinhDiemTB.setOnAction(event -> {
            TinhDiemTB_MonHoc();
            TinhDiemTB_HocKy();
        });
    }

    private void TinhDiemTB_HocKy() {
        for(KqSinnhVienHocKy kq : DiemSV_HocKy.getRepository().findAllExceptDiem()){
            if(DiemSV_HocKy.getRepository().getDiemTK(kq.getMaSinhVien(),kq.getMaHocKy(), kq.getMaNamHoc())==null && DiemSV_HocKy.getRepository().getTongTC(kq.getMaSinhVien(),kq.getMaHocKy(),kq.getMaNamHoc())==null){
                List<DKHocPhanEntity> kqSvMonHoc = DKHocPhan.getRepository().findAll();
                float TongDiemTK = 0;
                Integer TongTC = 0;
                for(String MH : DKHocPhan.getRepository().getMonhocDK(kq.getMaSinhVien())){
                    if(KqMonHoc_SV.getRepository().getDiemTK(kq.getMaSinhVien(), MH)!=null) {
                        TongDiemTK += KqMonHoc_SV.getRepository().getDiemTK(kq.getMaSinhVien(), MH) * MonHoc.getRepository().getSTC(MH);
                        TongTC += MonHoc.getRepository().getSTC(MH);
                        System.out.println("diem: "+TongDiemTK);
                        System.out.println("Tc: "+TongTC);
                    }
                }
                DiemSV_HocKy.getRepository().updateDiemTK(kq.getMaSinhVien(),kq.getMaHocKy(),kq.getMaNamHoc(), (float) (Math.round(TongDiemTK/TongTC * 100.0)/100.0));
                DiemSV_HocKy.getRepository().updateTongTC(kq.getMaSinhVien(),kq.getMaHocKy(),kq.getMaNamHoc(), TongTC);
            }
        }
    }

    private void TinhDiemTB_MonHoc(){
        try {
            for (KqSinhVienMonhocEntity kq : KqMonHoc_SV.getRepository().findAll()) {
                if (KqMonHoc_SV.getRepository().getDiemTK(kq.getMaSinhVien(), kq.getMaMonHoc()) == null) {
                    List<Float> diems = Diem.getRepository().getDiems(kq.getMaSinhVien(), kq.getMaMonHoc());
                    if(!diems.isEmpty()) {
                        float max = Collections.max(diems);
                        float TyLe = MonHoc.getRepository().getTyLeDiemQT(kq.getMaMonHoc());
                        Float diemqt = KqMonHoc_SV.getRepository().getDiemQT(kq.getMaSinhVien(), kq.getMaMonHoc());
                        float DiemTongKet = diemqt * (TyLe / 100) + max * ((100 - TyLe) / 100);
                        KqMonHoc_SV.getRepository().UpdateDiemTK(kq.getMaSinhVien(), kq.getMaMonHoc(), DiemTongKet);
                    }
                }
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
