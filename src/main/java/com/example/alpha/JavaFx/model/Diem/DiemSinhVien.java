package com.example.alpha.JavaFx.model.Diem;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class DiemSinhVien {
    private final StringProperty SvSelected;
    private final DoubleProperty DiemTK;
    private final DoubleProperty SoTC;
    private final StringProperty XepLoai;

    private final DoubleProperty DiemThi;

    public DiemSinhVien() {
        this.DiemTK = new SimpleDoubleProperty();
        this.SoTC = new SimpleDoubleProperty();
        this.XepLoai = new SimpleStringProperty();
        this.SvSelected = new SimpleStringProperty();
        this.DiemThi = new SimpleDoubleProperty();
    }
}
