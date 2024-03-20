package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

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

    FXMLLoader loader;
    Parent root;

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
                default -> borderPane.setRight(Model.getInstant().getViewFactory().getDashboard());
            }
        });
    }

    public void LoadScenceHBox(String File_FXML) throws IOException {
        loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/com/example/alpha/"+File_FXML))));
        root = loader.load();
        HBox_Main.getChildren().clear();
        HBox_Main.getChildren().add(root);
    }
    public void LoadScence(String File_FXML) throws IOException {
//        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/com/example/alpha/"+File_FXML))));
//        Parent root = loader.load();
//        anchorPane.getChildren().clear();
//        anchorPane.getChildren().add(root);
            try {
                FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/com/example/alpha/" + File_FXML))));
                Parent root = loader.load();
                loader.setController(this);
                AnchorPane_Main.getChildren().clear();
                AnchorPane_Main.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    public void Button_TongQuanAction(ActionEvent event) throws IOException {
//        LoadScence("TongQuan.fxml");
//        TongQuanController controller = loader.getController();
    }

    @FXML
    public void Button_QuanLyAction(ActionEvent event) throws IOException {
        LoadScenceHBox("QuanLy.fxml");
    }

    @FXML
    public void Button_ThongKeAction(ActionEvent event) throws IOException {
//        LoadScence("TongQuan.fxml");
//        TongQuanController controller = loader.getController();
    }

    public AnchorPane getAnchorPane_Main(String FXML_File) throws IOException {
        if(AnchorPane_Main == null){
            AnchorPane_Main = new FXMLLoader(getClass().getResource("/com/example/alpha/"+FXML_File)).load();
        }
        System.out.println(AnchorPane_Main);
        return AnchorPane_Main;
    }

    public void setAnchorPane_Main(AnchorPane anchorPane_Main) {
        AnchorPane_Main = anchorPane_Main;
    }
}