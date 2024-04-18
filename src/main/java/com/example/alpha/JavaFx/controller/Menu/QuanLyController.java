package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Diem.DiemSV_CaNam;
import com.example.alpha.JavaFx.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.model.SinhVien.DKHocPhan;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.Status;
import com.example.alpha.JavaFx.view.QuanLy;
import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinnhVienHocKy;
import com.example.alpha.Spring_boot.student.DKHocPhanEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
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
    private Button Button_TinhDiemTB;

    @FXML
    private Button Button_AutoNhapDuLieu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_Diem.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.DiemThi));
        button_PhanCong.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.DiemQT));
        button_TaiKhoan.setOnAction(event -> Model.getInstant().getViewQuanLy().getQuanLyProperty().set(QuanLy.TaiKhoan));
        Button_TinhDiemTB.setOnAction(event -> {
            TinhDiemTB_MonHoc();
            TinhDiemTB_HocKy();
            TinhDiemTB_NamHoc();
        });
        Button_AutoNhapDuLieu.setOnAction(event -> {
            addDataKqSV();
        });
    }

    private void addDataKqSV() {
        for(DKHocPhanEntity dkHocPhan : DKHocPhan.getRepository().findAll()){
            KqMonHoc_SV.getRepository().save(new KqSinhVienMonhocEntity(dkHocPhan.getMaSV(),dkHocPhan.getMaMH(),dkHocPhan.getHocKy(), dkHocPhan.getNamHoc()));
            DiemSV_HocKy.getRepository().save(new KqSinnhVienHocKy(dkHocPhan.getMaSV(), dkHocPhan.getHocKy(), dkHocPhan.getNamHoc()));
            DiemSV_CaNam.getRepository().save(new KqSinhVienCanamEntity(dkHocPhan.getMaSV(), dkHocPhan.getNamHoc()));
        }
    }

    private void TinhDiemTB_NamHoc() {
        for(KqSinnhVienHocKy kq : DiemSV_HocKy.getRepository().findAllExceptDiem()) {
            float TBhk1 = DiemSV_HocKy.getRepository().getDiemTKHK1(kq.getMaSinhVien(), kq.getMaNamHoc());
            float TBhk2 = DiemSV_HocKy.getRepository().getDiemTKHK2(kq.getMaSinhVien(), kq.getMaNamHoc());
            float TbCaNam = (TBhk1+TBhk2) /2;

            DiemSV_CaNam.getRepository().UpdateDiemHK1(kq.getMaSinhVien(), TBhk1, kq.getMaNamHoc());
            DiemSV_CaNam.getRepository().UpdateDiemHK2(kq.getMaSinhVien(),TBhk2, kq.getMaNamHoc());
            DiemSV_CaNam.getRepository().UpdateDiemCaNam(kq.getMaSinhVien(), (float) (Math.round(TbCaNam * 100.0)/100.0), kq.getMaNamHoc());
        }
    }

    private void TinhDiemTB_HocKy() {
        for(KqSinnhVienHocKy kq : DiemSV_HocKy.getRepository().findAllExceptDiem()){
//            if(DiemSV_HocKy.getRepository().getDiemTK(kq.getMaSinhVien(),kq.getMaHocKy(), kq.getMaNamHoc())==null && DiemSV_HocKy.getRepository().getTongTC(kq.getMaSinhVien(),kq.getMaHocKy(),kq.getMaNamHoc())==null)
                float TongDiemTK = 0;
                float TongTC = 0;
                for(String MH : DKHocPhan.getRepository().getMonhocDK(kq.getMaSinhVien())){
                    if(KqMonHoc_SV.getRepository().getDiemTK(kq.getMaSinhVien(),
                            MH,
                            kq.getMaHocKy(),
                            kq.getMaNamHoc())!=null) {
                        TongDiemTK += KqMonHoc_SV.getRepository().getDiemTK(
                                kq.getMaSinhVien(),
                                MH,
                                kq.getMaHocKy(),
                                kq.getMaNamHoc()) * MonHoc.getRepository().getSTC(MH);
                        TongTC += MonHoc.getRepository().getSTC(MH);
                    }
                }
                DiemSV_HocKy.getRepository().updateDiemTK(kq.getMaSinhVien(),kq.getMaHocKy(),kq.getMaNamHoc(), (float) (Math.round(TongDiemTK/TongTC * 100.0)/100.0));
                DiemSV_HocKy.getRepository().updateTongTC(kq.getMaSinhVien(),kq.getMaHocKy(),kq.getMaNamHoc(), (int) TongTC);
//            }
        }
    }

    private void TinhDiemTB_MonHoc(){
        try {
            for (KqSinhVienMonhocEntity kq : KqMonHoc_SV.getRepository().findAll()) {
//                if (KqMonHoc_SV.getRepository().getDiemTK(kq.getMaSinhVien(), kq.getMaMonHoc()) == null) {
                    List<Float> diems = Diem.getRepository().getDiems(kq.getMaSinhVien(), kq.getMaMonHoc());
                    if(!diems.isEmpty()) {
                        float max = Collections.max(diems);
                        float TyLe = MonHoc.getRepository().getTyLeDiemQT(kq.getMaMonHoc());
                        Float diemqt = KqMonHoc_SV.getRepository().getDiemQT(kq.getMaSinhVien(), kq.getMaMonHoc());
                        if(diemqt==null) {
                            Model.getInstant().getViewFactory().showLog("Bạn chưa nhập điểm quá trình cho Sinh viên:" +"\nMaSV: " + kq.getMaSinhVien() +"\nMaMonHoc: "+ kq.getMaMonHoc() +"\nHocKy:  "+ kq.getMaHocKy() +"\nNamHoc: "+ kq.getMaNamHoc());
                            Model.getInstant().getViewFactory().getStatus().set(Status.ERROR);
                        }else {
                            float DiemTongKet = diemqt * (TyLe / 100) + max * ((100 - TyLe) / 100);

                            KqMonHoc_SV.getRepository().UpdateDiemThi(kq.getMaSinhVien(), kq.getMaMonHoc(), max, kq.getMaHocKy(), kq.getMaNamHoc());
                            KqMonHoc_SV.getRepository().UpdateDiemTK(kq.getMaSinhVien(),
                                    kq.getMaMonHoc(),
                                    (float) (Math.round(DiemTongKet * 100.0)/100.0)
                            );

                            Model.getInstant().getViewFactory().getStatus().set(Status.OK);
                        }
                    }
//                }
            }
            Model.getInstant().getViewFactory().getLog().set("OK");
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
