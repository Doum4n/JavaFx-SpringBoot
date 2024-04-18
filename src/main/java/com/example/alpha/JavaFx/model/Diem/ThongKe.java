package com.example.alpha.JavaFx.model.Diem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class ThongKe {
    private final StringProperty search;

    public ThongKe() {
        this.search = new SimpleStringProperty();
    }
}
