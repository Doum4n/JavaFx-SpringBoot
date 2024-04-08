package com.example.alpha.JavaFx.model;

import javafx.scene.control.*;
import lombok.Getter;

@Getter
public class ButtonCellSV {

    private final Button button;

    public ButtonCellSV() {
        this.button = new Button("Xem");
        button.setOnAction(event -> {
            TableCell tableCell = (TableCell) button.getParent().getParent().lookup("#Column_MaSV");
            Model.getInstant().getViewQuanLy().getSvSelected().set(tableCell.getItem().toString());
        });
    }

}

