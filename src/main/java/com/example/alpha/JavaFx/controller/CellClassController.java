package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CellClassController implements Initializable {
    @FXML
    private Label Label_Lop;

    @FXML
    private Button buttonn_Xem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label_Lop.setText(Model.getInstant().getCellGiaoVien().getMaLop().get());
    }
}
