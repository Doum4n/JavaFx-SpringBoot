package com.example.alpha.JavaFx.view;

import com.example.alpha.JavaFx.controller.AccountType;
import com.example.alpha.JavaFx.controller.LoginController;
import com.example.alpha.JavaFx.model.NamHoc;
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
    private AccountType type;
    private HBox QuanLy;
    private AnchorPane dashboard;

    private final ObjectProperty<Menu> menuProperty;

    private final StringProperty NamHoc;
    private final StringProperty Hocky;
    private Stage stage = new Stage();

    public viewFactory(){
        this.type = AccountType.Student;
        this.menuProperty = new SimpleObjectProperty<>();
        this.NamHoc = new SimpleStringProperty();
        this.Hocky = new SimpleStringProperty();
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

    public void showEditSV(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/EditSV.fxml"))));
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
