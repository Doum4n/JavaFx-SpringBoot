package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class WorkplaceController implements Initializable{
    public BorderPane borderPane;

    @FXML
    private AnchorPane AnchorPane_HBox;
    @FXML
    protected AnchorPane AnchorPane_Main;

    @FXML
    private HBox HBox_Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*AnchorPane_Menu.setTranslateX(-187);
        Label_Menu.setOnMouseClicked(event -> {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.seconds(0.4));
            translateTransition.setNode(AnchorPane_Menu);

            translateTransition.setToX(-187);
            translateTransition.play();

            AnchorPane_Menu.setTranslateX(0);

            translateTransition.setOnFinished((ActionEvent e) -> {
                Label_Menu.setVisible(false);
                Label_Menu_close.setVisible(true);
            });
        });
        Label_Menu_close.setOnMouseClicked(event -> {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.seconds(0.4));
            translateTransition.setNode(AnchorPane_Menu);

            translateTransition.setToX(0);
            translateTransition.play();

            AnchorPane_Menu.setTranslateX(-187);

            translateTransition.setOnFinished((ActionEvent e) -> {
                Label_Menu.setVisible(true);
                Label_Menu_close.setVisible(false);
            });
        });*/

        Model.getInstant().getViewFactory().getStringProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case "QuanLy" -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Model.getInstant().getViewFactory().getQuanLyView());
                    }
                }
                case "QLSinhVien" -> borderPane.setRight(Model.getInstant().getViewFactory().getQLSinhVien());
                case "KetQua" -> borderPane.setRight(Model.getInstant().getViewFactory().getDiem());
                case "PhanLop" -> borderPane.setRight(Model.getInstant().getViewFactory().getPhanLop());
                default -> borderPane.setRight(Model.getInstant().getViewFactory().getDashboard());
            }
        });
    }
}