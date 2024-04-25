package com.example.alpha.JavaFx.role_student.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class DiemSinnVien {
    private final StringProperty MaSV;
    private final StringProperty HocKy;
    private final StringProperty NamHoc;
    private final StringProperty HocKy_before;
    private final StringProperty NamHoc_before;

    public DiemSinnVien() {
        MaSV = new SimpleStringProperty();
        HocKy = new SimpleStringProperty();
        NamHoc = new SimpleStringProperty();
        HocKy_before = new SimpleStringProperty();
        NamHoc_before = new SimpleStringProperty();
    }
}
