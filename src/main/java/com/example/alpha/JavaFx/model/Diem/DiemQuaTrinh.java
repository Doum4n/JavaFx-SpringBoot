package com.example.alpha.JavaFx.model.Diem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class DiemQuaTrinh {
    private final StringProperty Diem;
    private final StringProperty MaSV;
    private final BooleanProperty isUpdate;
    private final StringProperty LopSelected;
    private final StringProperty MaGVSelected;
    private final StringProperty MaMHSelected;

    public DiemQuaTrinh(){
        this.Diem = new SimpleStringProperty();
        this.MaSV = new SimpleStringProperty();
        this.isUpdate = new SimpleBooleanProperty();
        this.LopSelected = new SimpleStringProperty();
        this.MaGVSelected = new SimpleStringProperty();
        this.MaMHSelected = new SimpleStringProperty();
    }
}
