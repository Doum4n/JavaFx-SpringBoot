package com.example.alpha.JavaFx.role_admin.model.GiaoVien;

import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import lombok.Getter;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.List;

@Getter
public class CellGiaoVien {
    private final StringProperty MaGV;
    private final StringProperty TenGV;
    private final StringProperty MaMH;
    private final StringProperty MaLop;
    private final StringProperty DiaChi;
    private final StringProperty SDT;

    private final IntegerProperty SlLop;
    private final IntegerProperty SlGiaoVien;

    public CellGiaoVien(){
        this.MaGV = new SimpleStringProperty();
        this.TenGV = new SimpleStringProperty();
        this.MaMH = new SimpleStringProperty();
        this.MaLop = new SimpleStringProperty();
        this.DiaChi = new SimpleStringProperty();
        this.SDT = new SimpleStringProperty();

        this.SlLop = new SimpleIntegerProperty(1);
        this.SlGiaoVien = new SimpleIntegerProperty(1);
    }
}
