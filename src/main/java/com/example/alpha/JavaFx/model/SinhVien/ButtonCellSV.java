package com.example.alpha.JavaFx.model.SinhVien;

import com.example.alpha.JavaFx.model.Model;
import javafx.scene.control.*;
import lombok.Getter;

@Getter
public class ButtonCellSV {

    private final Button button;

    public ButtonCellSV() {
        this.button = new Button("Xem");
        button.setOnAction(event -> {
            TableCell tableCell = (TableCell) button.getParent().getParent().lookup("#Column_MaSV");
            Model.getInstant().getDiemSinhVien().getSvSelected().set(tableCell.getItem().toString());
        });
    }

}

