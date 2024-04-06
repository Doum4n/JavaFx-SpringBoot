package com.example.alpha.JavaFx.model;

import com.example.alpha.Spring_boot.student.SinhVienEntity;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import lombok.Getter;

@Getter
public class ButtonCell extends TableCell<SinhVienEntity, Button> {

    private final Button button;

    public ButtonCell() {
        this.button = new Button("Xem");
        button.setOnAction(event -> {
            TableCell tableCell = (TableCell) button.getParent().getParent().lookup("#Column_MaSV");
            Model.getInstant().getViewQuanLy().getSvSelected().set(tableCell.getItem().toString());
        });
    }

    @Override
    protected void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(item);
            setText(null); // Hiển thị giá trị String nếu có
        }
    }

}

