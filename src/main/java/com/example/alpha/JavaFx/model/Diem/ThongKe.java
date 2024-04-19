package com.example.alpha.JavaFx.model.Diem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class ThongKe {
    private final StringProperty search_SV;
    private final StringProperty search_Lop;

    public ThongKe() {
        this.search_SV = new SimpleStringProperty();
        this.search_Lop = new SimpleStringProperty();
    }
}
