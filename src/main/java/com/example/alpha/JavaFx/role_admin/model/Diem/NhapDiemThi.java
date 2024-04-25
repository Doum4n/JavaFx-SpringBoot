package com.example.alpha.JavaFx.role_admin.model.Diem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class NhapDiemThi {
    private final StringProperty MonHocSelected;
    private final StringProperty PhongThiProperty;
    private final StringProperty LanThi;

    public NhapDiemThi() {
        this.MonHocSelected = new SimpleStringProperty();
        this.PhongThiProperty = new SimpleStringProperty();
        this.LanThi = new SimpleStringProperty();
    }
}
