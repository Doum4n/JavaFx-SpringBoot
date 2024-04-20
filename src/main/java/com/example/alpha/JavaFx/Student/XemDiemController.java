package com.example.alpha.JavaFx.Student;

import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XemDiemController implements Initializable {

    @FXML
    private Button Button_profile;

    @FXML
    private VBox VBox_Diem;
    @FXML
    private AnchorPane AnchorPane_main;

    @FXML
    private ScrollPane ScrollPane_main;

    @FXML
    private Label Label_Lop;

    @FXML
    private Label Label_MaSV;

    @FXML
    private Label Label_TenSV;

    @FXML
    private Button Button_logOut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScrollPane_main.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        AnchorPane_main.setPrefHeight(Region.USE_COMPUTED_SIZE);
        VBox_Diem.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Diem.getRepository().getNamHocByMaSV("1").stream().distinct().forEach(NamHoc -> Diem.getRepository().getHocKyByMaSV("1").forEach(HocKy -> {
            Singleton.getInstant().getDiemSinnVienStudent().getHocKy().set(HocKy);
            Singleton.getInstant().getDiemSinnVienStudent().getNamHoc().set(NamHoc);
            Singleton.getInstant().getDiemSinnVienStudent().getMaSV().set("1");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/alpha/ThongKe/DiemSV.fxml"));
            TableView<DiemEntity> tableView;
            try {
                tableView = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader loader1 = new FXMLLoader();
            AnchorPane pane;
            loader1.setLocation(getClass().getResource("/com/example/alpha/SinhVienRole/DiemTKSinhVien.fxml"));
            try {
                pane = loader1.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            VBox_Diem.getChildren().addAll(tableView, pane);
        }));

        Label_MaSV.setText(Singleton.getInstant().getDiemSinnVienStudent().getMaSV().get());
        Label_Lop.setText(PhanLop.getRepository().getLop(Singleton.getInstant().getDiemSinnVienStudent().getMaSV().get()));
        Label_TenSV.setText(SinhVien.getRepository().getByHoTen(Singleton.getInstant().getDiemSinnVienStudent().getMaSV().get()));

        Label_MaSV.setPrefWidth(Region.USE_COMPUTED_SIZE);
        Label_Lop.setPrefWidth(Region.USE_COMPUTED_SIZE);
        Label_TenSV.setPrefWidth(Region.USE_COMPUTED_SIZE);

        Button_logOut.setOnAction(event -> {
            Singleton.getInstant().getViewFactory().showLoginWindow();
        });
    }
}
