package com.example.alpha.JavaFx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class DiemQuaTrinh {
    private StringProperty Diem;

    public DiemQuaTrinh(){
        this.Diem = new SimpleStringProperty();
    }
}
