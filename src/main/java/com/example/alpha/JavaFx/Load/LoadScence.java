package com.example.alpha.JavaFx.Load;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class LoadScence {
    private static Parent root;
    private static Scene scene;
    private static Stage stage;
    public static void LoadScence(ActionEvent event, String File_FXML) throws IOException {
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoadScence.class.getResource("/com/example/alpha/"+File_FXML))));
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
