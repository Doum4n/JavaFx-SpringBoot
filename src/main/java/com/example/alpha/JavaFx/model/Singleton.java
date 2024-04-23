package com.example.alpha.JavaFx.model;

import com.example.alpha.JavaFx.Student.view.viewDiemSinhVien;
import com.example.alpha.JavaFx.model.Diem.DiemQuaTrinh;
import com.example.alpha.JavaFx.model.Diem.DiemSinhVien;
import com.example.alpha.JavaFx.model.Diem.NhapDiemThi;
import com.example.alpha.JavaFx.model.Diem.ThongKe;
import com.example.alpha.JavaFx.model.GiaoVien.CellGiaoVien;
import com.example.alpha.JavaFx.view.viewFactory;
import com.example.alpha.JavaFx.view.viewQuanLy;
import com.example.alpha.JavaFx.view.viewThongKe;
import com.example.alpha.JavaFx.view.viewDanhGia;
import com.example.alpha.JavaFx.Student.model.DiemSinnVien;
import lombok.Getter;

//@Component
@Getter
public class Singleton {
    private static Singleton singleton;
    private final viewFactory viewFactory;
    private final viewQuanLy viewQuanLy;
    private final viewThongKe viewThongKe;
    private final viewDanhGia viewDanhGia;

    private final viewDiemSinhVien viewDiemSinhVien;
    private final DiemSinnVien diemSinnVienStudent;

    private final QuanLyTaiKhoan quanLyTaiKhoan;

    private final ThongKe thongKe;
    private final CellGiaoVien cellGiaoVien;
    private final NhapDiemThi nhapDiemThi;
    private final DiemQuaTrinh diemQuaTrinh;
    private final DiemSinhVien diemSinhVien;
    private final DSTop10percentSV_Lop dsTop10percentSVLop;

    public Singleton(){
        this.viewDanhGia = new viewDanhGia();
        this.viewQuanLy = new viewQuanLy();
        this.viewFactory = new viewFactory();
        this.viewThongKe = new viewThongKe();

        this.quanLyTaiKhoan = new QuanLyTaiKhoan();

        this.viewDiemSinhVien = new viewDiemSinhVien();
        this.diemSinnVienStudent = new DiemSinnVien();

        this.thongKe = new ThongKe();
        this.cellGiaoVien = new CellGiaoVien();
        this.nhapDiemThi = new NhapDiemThi();
        this.diemQuaTrinh = new DiemQuaTrinh();
        this.diemSinhVien = new DiemSinhVien();
        this.dsTop10percentSVLop = new DSTop10percentSV_Lop();
    }

    public static synchronized Singleton getInstant(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
