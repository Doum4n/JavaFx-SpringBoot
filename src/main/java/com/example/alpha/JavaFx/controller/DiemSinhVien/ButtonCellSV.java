package com.example.alpha.JavaFx.controller.DiemSinhVien;

import com.example.alpha.JavaFx.model.Singleton;
import javafx.scene.control.*;
import lombok.Getter;

@Getter
public class ButtonCellSV {

    private final Button button;

    public ButtonCellSV() {
        this.button = new Button("Xem");
        button.setOnAction(event -> {
            TableCell tableCell = (TableCell) button.getParent().getParent().lookup("#Column_MaSV");
            Singleton.getInstant().getDiemSinhVien().getSvSelected().set(tableCell.getItem().toString());
        });
    }

}

