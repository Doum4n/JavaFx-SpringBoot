package com.example.alpha.JavaFx.role_admin.model.Diem;

import javafx.beans.property.*;
import javafx.scene.layout.Pane;
import lombok.Getter;

@Getter
public class DiemQuaTrinh {
    private final StringProperty Diem;
    private final StringProperty MaSV;
    private final BooleanProperty isUpdate;
    private final StringProperty LopSelected;
    private final StringProperty MaGVSelected;
    private final StringProperty MaMHSelected;
    private final StringProperty Search_SV;
    private final StringProperty Search_GV;
    private final StringProperty pane;

    private final BooleanProperty reLoad;

    public DiemQuaTrinh(){
        this.Diem = new SimpleStringProperty();
        this.MaSV = new SimpleStringProperty();
        this.isUpdate = new SimpleBooleanProperty();
        this.LopSelected = new SimpleStringProperty();
        this.MaGVSelected = new SimpleStringProperty();
        this.MaMHSelected = new SimpleStringProperty();
        this.Search_SV = new SimpleStringProperty();
        this.Search_GV = new SimpleStringProperty();
        this.pane = new SimpleStringProperty();
        this.reLoad = new SimpleBooleanProperty();
    }
}
