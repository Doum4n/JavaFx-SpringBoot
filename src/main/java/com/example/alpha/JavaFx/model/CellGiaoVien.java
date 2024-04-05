package com.example.alpha.JavaFx.model;

import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
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

    public CellGiaoVien(){
        this.MaGV = new SimpleStringProperty(""/*giaovienEntities.get(0).getMaGiaoVien()*/);
        this.TenGV = new SimpleStringProperty(/*giaovienEntities.get(0).getTenGiaoVien()*/);
        this.MaMH = new SimpleStringProperty(/*phancongEntities.get(0).getMaMonHoc()*/);
        this.MaLop = new SimpleStringProperty(/*phancongEntities.get(0).getMaLop()*/);
        this.DiaChi = new SimpleStringProperty();
        this.SDT = new SimpleStringProperty();
    }
}
