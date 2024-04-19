package com.example.alpha.JavaFx.controller.GiaoVien;

import com.example.alpha.JavaFx.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

            // Get references to all labels in one step using getChildList()

           /* Model.getInstant().getDiemQuaTrinh().getLopSelected().set(Label_Lop.getText());
            List<Label> labels = buttonn_Xem.getParent().getParent().getParent().getChildrenUnmodifiable().stream()
                    .filter(child -> child instanceof Label)
                    .map(child -> (Label) child)
                    .toList();

            if (!labels.isEmpty()) { // Check if any labels were found
                for (Label label : labels) {
                    String labelId = label.getId(); // Extract the label ID
                    String value = label.getText(); // Get the label's text

                    switch (labelId) {
                        case "Label_MaMH":
                            Model.getInstant().getDiemQuaTrinh().getMaMHSelected().set(value);
                            break;
                        case "Label_MaGV":
                            Model.getInstant().getDiemQuaTrinh().getMaGVSelected().set(value);
                            break;
                    }
                }
            }*/

        });
    }
}
