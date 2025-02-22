package com.example.alpha.JavaFx.role_admin.view;

import com.example.alpha.JavaFx.role_admin.controller.quan_ly.TaiKhoan.AccountType;
import com.example.alpha.JavaFx.role_admin.controller.Menu.LoginController;
import com.example.alpha.JavaFx.role_admin.controller.Menu.TienTrinh;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.Status;
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

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class viewFactory {
    private AccountType type;
    private HBox QuanLy;
    private HBox DanhGia;
    private HBox ThongKe;
    private AnchorPane QLTaiKhoan;

    private final ObjectProperty<Menu> menuProperty;

    private final StringProperty NamHoc;
    private final StringProperty Hocky;
    private final StringProperty log;
    private Stage stage = new Stage();

    //Khi đăng nhập bằng tư cách sinh viên
    private final StringProperty username;
    private final StringProperty password;

    private final ObjectProperty<TienTrinh> TienTrinh;

    public viewFactory(){
        this.type = AccountType.Student;
        this.menuProperty = new SimpleObjectProperty<>();

        LocalDateTime currentTime = LocalDateTime.now();
        this.NamHoc = new SimpleStringProperty(String.valueOf(currentTime.getYear()));
        this.Hocky = new SimpleStringProperty(currentTime.getMonthValue()<7?"1":"2");
        this.log = new SimpleStringProperty();

        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.TienTrinh = new SimpleObjectProperty<>();
    }

    public HBox getQuanLyView(){
        try {
            if (QuanLy == null) {
                QuanLy = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/Workplace/NhapDiem.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QuanLy;
    }

    public AnchorPane getQLTaiKhoan() {
        try {
            if (QLTaiKhoan == null) {
                QLTaiKhoan = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/QuanLyTaiKhoan/TaiKhoan.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QLTaiKhoan;
    }

    public HBox getDanhGia(){
        try {
            if (DanhGia == null) {
                DanhGia = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/Workplace/DanhGia.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DanhGia;
    }


    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/role_admin/login.fxml"))));
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
                ThongKe = new FXMLLoader(getClass().getResource("/com/example/alpha/fxml/role_admin/Workplace/ThongKe.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ThongKe;
    }

    public void showWorkPlaceWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/role_admin/Workplace/workplace.fxml"))));
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

        Singleton.getInstant().getViewFactory().getLog().set(log);
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/role_admin/Log.fxml"))));
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

    public void showChangePasswordWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/ChangePassword.fxml"))));
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thay đổi mật khẩu");
        stage.show();
    }

    public void showCreateAccountWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/role_admin/QuanLyTaiKhoan/CreateAccount.fxml"))));
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tạo tài khoản");
        stage.show();
    }

    public void showStudentWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/role_sinhvien/Student.fxml"))));
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

    public void showTeacherWindow(){
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(LoginController.class.getResource("/com/example/alpha/fxml/role_giaovien/Teacher.fxml"))));
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
