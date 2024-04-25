package com.example.alpha.JavaFx.role_admin.model.MonHoc;

import com.example.alpha.JavaFx.role_admin.model.Singleton;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import lombok.Getter;

@Getter
public class ButtonCellMH {
    private final Button button;

    public ButtonCellMH() {
        this.button = new Button("Xem");
        button.setOnAction(event -> {
            TableCell tableCell = (TableCell) button.getParent().getParent().lookup("#Column_MaMH");
            Singleton.getInstant().getNhapDiemThi().getMonHocSelected().set(tableCell.getItem().toString());
        });
    }
}
