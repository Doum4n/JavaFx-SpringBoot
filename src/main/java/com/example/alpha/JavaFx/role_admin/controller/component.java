package com.example.alpha.JavaFx.role_admin.controller;

import com.example.alpha.JavaFx.role_admin.model.Lop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class component {
    public static void Auto_Suggest(ComboBox<String> comboBoxLop) {
        comboBoxLop.getEditor().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            var suggestion = FXCollections.observableArrayList(Lop.getRepository().getAllLop());

            ObservableList<String> S = FXCollections.observableArrayList();
            for (String s : suggestion) {
                if (!s.equals(comboBoxLop.getEditor().getText())) {
                    if (s.contains(comboBoxLop.getEditor().getText())) {
                        S.add(s);
                    }
                }
            }
            if(!event.getCode().equals(KeyCode.ENTER)) {
                comboBoxLop.setItems(S);
                comboBoxLop.show();
            }
        });
    }
}
