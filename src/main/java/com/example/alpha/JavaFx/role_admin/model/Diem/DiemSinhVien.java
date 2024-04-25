package com.example.alpha.JavaFx.role_admin.model.Diem;

import javafx.beans.property.*;
import lombok.Getter;

@Getter
public class DiemSinhVien {
    private final StringProperty SvSelected;
    private final FloatProperty DiemTK;
    private final DoubleProperty SoTC;
    private final StringProperty XepLoai;

    private final DoubleProperty DiemThi;

    public DiemSinhVien() {
        this.DiemTK = new SimpleFloatProperty();
        this.SoTC = new SimpleDoubleProperty();
        this.XepLoai = new SimpleStringProperty();
        this.SvSelected = new SimpleStringProperty();
        this.DiemThi = new SimpleDoubleProperty();
    }
}
