package com.example.alpha.JavaFx.controller.TaiKhoan;

import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.Spring_boot.user.NguoidungEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class TaiKhoanController implements Initializable{
    @FXML
    private Tab Tab_GV;

    @FXML
    private Tab Tab_SV;
    @FXML
    private TabPane Tabpane_TaiKhoan;
    @FXML
    private TableView<NguoidungEntity> TableView_TaiKhoan;

    @FXML
    private TableView<?> TableView_TaiKhoan1;

    @FXML
    private TableColumn<?, ?> column_MaTK;

    @FXML
    private TableColumn<?, ?> column_MaTK1;

    @FXML
    private TableColumn<?, ?> column_Password;

    @FXML
    private TableColumn<?, ?> column_TenNguoiDung;

    @FXML
    private TableColumn<?, ?> column_Password1;

    @FXML
    private TableColumn<?, ?> column_TenTK;

    @FXML
    private TableColumn<?, ?> column_TenTK1;

    private StringProperty Tab_selected = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Tabpane_TaiKhoan.getTabs().get(0).getContent());
        Tabpane_TaiKhoan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.getText().equals("Sinh viên")){
                Singleton.getInstant().getViewQuanLy().getTab_selected().set("Sinh viên");
            }else {
                Singleton.getInstant().getViewQuanLy().getTab_selected().set("Giáo viên");
            }
        });
    }
}
