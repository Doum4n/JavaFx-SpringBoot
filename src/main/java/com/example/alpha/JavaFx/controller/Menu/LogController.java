package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class LogController implements Initializable {
    @FXML
    private TextFlow TextFlow_Log;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFlow_Log.getChildren().clear();
        TextFlow_Log.getChildren().add(new Text(Singleton.getInstant().getViewFactory().getLog().get()));
    }
}
