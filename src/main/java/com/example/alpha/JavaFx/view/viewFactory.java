package com.example.alpha.JavaFx.view;

import com.example.alpha.JavaFx.controller.LoginController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Objects;

//@Component
public class viewFactory {
    private HBox QuanLy;
    private AnchorPane dashboard;

    private AnchorPane QLSinhVien;
    private final StringProperty stringProperty;
    private Stage stage = new Stage();

    public viewFactory(){
        this.stringProperty = new SimpleStringProperty("");
    }

    public StringProperty getStringProperty(){
        return stringProperty;
    }

    public HBox getQuanLyView(){
        try {
            if (QuanLy == null) {
                QuanLy = new FXMLLoader(getClass().getResource("/com/example/alpha/QuanLy.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QuanLy;
    }

    public AnchorPane getDashboard(){
        try {
            if (dashboard == null) {
                dashboard = new FXMLLoader(getClass().getResource("/com/example/alpha/TongQuan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dashboard;
    }

    public AnchorPane getQLSinhVien(){
        try {
            if (QLSinhVien == null) {
                QLSinhVien = new FXMLLoader(getClass().getResource("/com/example/alpha/SinhVien.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QLSinhVien;
    }



    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/login.fxml"))));
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
//        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showWorkPlaceWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/workplace.fxml"))));
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
//        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
