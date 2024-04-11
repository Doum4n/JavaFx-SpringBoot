package com.example.alpha.JavaFx.controller.GiaoVien;

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
        buttonn_Xem.setOnAction(event -> {

            Label MaMH = (Label) buttonn_Xem.getParent().getParent().getParent().lookup("#Label_MaMH");
            Model.getInstant().getDiemQuaTrinh().getMaMHSelected().set(MaMH.getText());

            Model.getInstant().getDiemQuaTrinh().getLopSelected().set(Label_Lop.getText());

            Label MaGV = (Label) buttonn_Xem.getParent().getParent().getParent().lookup("#Label_MaGV");
            Model.getInstant().getDiemQuaTrinh().getMaGVSelected().set(MaGV.getText());
        });
    }
}
