package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.view.Menu;
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

import static com.example.alpha.JavaFx.view.Menu.QuanLy;

@Controller
public class WorkplaceController implements Initializable{
    public BorderPane borderPane;

    @FXML
    private AnchorPane AnchorPane_HBox;

    @FXML
    private HBox HBox_Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstant().getViewFactory().getMenuProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case QuanLy -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Model.getInstant().getViewFactory().getQuanLyView());
                    }
                }
                default -> Model.getInstant().getViewFactory().getDashboard();
            }
        });
        Model.getInstant().getViewFactory().getQuanLyProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case QLSinhVien -> borderPane.setRight(Model.getInstant().getViewFactory().getQLSinhVien());
                case KetQua -> borderPane.setRight(Model.getInstant().getViewFactory().getDiem());
                case PhanLop -> borderPane.setRight(Model.getInstant().getViewFactory().getPhanLop());
                case QLGiaoVien -> borderPane.setRight(Model.getInstant().getViewFactory().getQLGiaoVien());
                case PhanCong -> borderPane.setRight(Model.getInstant().getViewFactory().getPhanCong());
            }
        });
    }
}