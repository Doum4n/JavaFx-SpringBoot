package com.example.alpha.JavaFx.view;

import com.example.alpha.JavaFx.controller.TaiKhoan.AccountType;
import com.example.alpha.JavaFx.controller.Menu.LoginController;
import com.example.alpha.JavaFx.model.HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.Status;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Data;

import java.util.Objects;

@Data
public class viewFactory {
    private AccountType type;
    private HBox QuanLy;
    private HBox DanhGia;
    private HBox ThongKe;

    private final ObjectProperty<Menu> menuProperty;

    private final StringProperty NamHoc;
    private final StringProperty Hocky;
    private final StringProperty log;
    private final ObjectProperty<Status> status;
    private Stage stage = new Stage();

    public viewFactory(){
        this.type = AccountType.Student;
        this.menuProperty = new SimpleObjectProperty<>();
        this.NamHoc = new SimpleStringProperty(com.example.alpha.JavaFx.model.NamHoc.getRepository().getNamHoc().get(0));
        this.Hocky = new SimpleStringProperty(HocKy.getRepository().getHocKy().get(0));
        this.log = new SimpleStringProperty();
        this.status = new SimpleObjectProperty<>(Status.OK);
    }

    public HBox getQuanLyView(){
        try {
            if (QuanLy == null) {
                QuanLy = new FXMLLoader(getClass().getResource("/com/example/alpha/Workplace/QuanLy.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QuanLy;
    }



    public HBox getDanhGia(){
        try {
            if (DanhGia == null) {
                DanhGia = new FXMLLoader(getClass().getResource("/com/example/alpha/Workplace/DanhGia.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DanhGia;
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

    public HBox getThongKe() {
        try {
            if (ThongKe == null) {
                ThongKe = new FXMLLoader(getClass().getResource("/com/example/alpha/Workplace/ThongKe.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ThongKe;
    }

    public void showWorkPlaceWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/Workplace/workplace.fxml"))));
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

    public void showLog(String log){

        Model.getInstant().getViewFactory().getLog().set(log);
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/Log.fxml"))));
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
