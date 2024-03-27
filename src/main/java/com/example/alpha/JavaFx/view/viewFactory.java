package com.example.alpha.JavaFx.view;

import com.example.alpha.JavaFx.controller.AccountType;
import com.example.alpha.JavaFx.controller.LoginController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
public class viewFactory {
    @Getter
    private AccountType type;
    private HBox QuanLy;

    private AnchorPane dashboard;
    private AnchorPane PhanLop;
    private AnchorPane QLSinhVien;
    private AnchorPane QLGiaoVien;
    private AnchorPane PhanCong;
    private AnchorPane Diem;

    private final ObjectProperty<Menu> menuProperty;
    private final ObjectProperty<QuanLy> quanLyProperty;
    private Stage stage = new Stage();

    public viewFactory(){
        this.quanLyProperty = new SimpleObjectProperty<>();
        this.type = AccountType.Student;
        this.menuProperty = new SimpleObjectProperty<>();
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

    public AnchorPane getQLGiaoVien(){
        try {
            if (QLGiaoVien == null) {
                QLGiaoVien = new FXMLLoader(getClass().getResource("/com/example/alpha/GiaoVien.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QLGiaoVien;
    }

    public AnchorPane getDiem(){
        try {
            if (Diem == null) {
                Diem = new FXMLLoader(getClass().getResource("/com/example/alpha/Diem.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Diem;
    }

    public AnchorPane getPhanLop(){
        try {
            if (PhanLop == null) {
                PhanLop = new FXMLLoader(getClass().getResource("/com/example/alpha/PhanLop.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PhanLop;
    }

    public AnchorPane getPhanCong(){
        try {
            if (PhanCong == null) {
                PhanCong = new FXMLLoader(getClass().getResource("/com/example/alpha/PhanCong.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PhanCong;
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

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/Admin.fxml"))));
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
