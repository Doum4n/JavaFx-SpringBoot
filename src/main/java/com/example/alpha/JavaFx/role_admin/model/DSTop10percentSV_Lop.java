package com.example.alpha.JavaFx.role_admin.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

import javax.print.DocFlavor;

@Getter
public class DSTop10percentSV_Lop {
    private final StringProperty Lop;

    public DSTop10percentSV_Lop() {
        this.Lop = new SimpleStringProperty(PhanLop.getRepository().getAllLop().get(0));
    }
}
