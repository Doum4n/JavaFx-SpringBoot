package com.example.alpha.JavaFx.model;

import com.example.alpha.JavaFx.view.viewFactory;
import com.example.alpha.JavaFx.view.viewQuanLy;
import lombok.Getter;

//@Component
@Getter
public class Model {
    private static Model model;
    private final viewFactory viewFactory;
    private final viewQuanLy viewQuanLy;
    private final CellGiaoVien cellGiaoVien;
    private final NhapDiemThi nhapDiemThi;
    private final DiemQuaTrinh diemQuaTrinh;

    public Model(){
        this.viewQuanLy = new viewQuanLy();
        this.viewFactory = new viewFactory();
        this.cellGiaoVien = new CellGiaoVien();
        this.nhapDiemThi = new NhapDiemThi();
        this.diemQuaTrinh = new DiemQuaTrinh();
    }

    public static synchronized Model getInstant(){
        if(model == null){
            model = new Model();
        }
        return model;
    }
}
