package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.HocKy;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.JavaFx.model.NamHoc;
import com.example.alpha.JavaFx.model.Status;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class WorkplaceController implements Initializable{
    @FXML
    @Getter
    private ChoiceBox<String> ChoiceBox_HocKy;

    @FXML
    @Getter
    private ChoiceBox<String> ChoiceBox_Nam;

    public BorderPane borderPane;

    @FXML
    private AnchorPane AnchorPane_HBox;

    @FXML
    private Label Label_Status;

    @FXML
    private HBox HBox_Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ChoiceBox_Nam.setItems(FXCollections.observableArrayList((NamHoc.getRepository().getNamHoc())));
        ChoiceBox_HocKy.setItems(FXCollections.observableArrayList((HocKy.getRepository().getHocKy())));

        Singleton.getInstant().getViewFactory().getMenuProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case QuanLy -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Singleton.getInstant().getViewFactory().getQuanLyView());
                    }
                    addListenerQuanLy();
                    Singleton.getInstant().getViewFactory().getStatus().set(Status.EDIT);
                }
                case DanhGia -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Singleton.getInstant().getViewFactory().getDanhGia());
                    }
                    addListenerDanhGia();
                    Singleton.getInstant().getViewFactory().getStatus().set(Status.INFO);
                }
                case ThongKe -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Singleton.getInstant().getViewFactory().getThongKe());
                    }
                    addListenerThongKe();
                    Singleton.getInstant().getViewFactory().getStatus().set(Status.INFO);
                }
            }
        });
        ChoiceBox_HocKy.setValue(HocKy.getRepository().getHocKy().get(0));
        ChoiceBox_Nam.setValue(NamHoc.getRepository().getNamHoc().get(0));

        addListenerChoiceBoc_Nam();
        addListenerChoiceBoc_HocKy();

        ChoiceBox_Nam.valueProperty().addListener((observable, oldValue, newValue) -> addListenerChoiceBoc_Nam());
        ChoiceBox_HocKy.valueProperty().addListener((observable, oldValue, newValue) -> addListenerChoiceBoc_HocKy());

        Label_Status.setText(Singleton.getInstant().getViewFactory().getStatus().get().toString());
        Label_Status.textProperty().bind(Singleton.getInstant().getViewFactory().getStatus().asString());
    }

    private void addListenerQuanLy(){
        Singleton.getInstant().getViewQuanLy().getQuanLyProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case DiemThi -> borderPane.setRight(Singleton.getInstant().getViewQuanLy().getDiem());
                case DiemQT -> borderPane.setRight(Singleton.getInstant().getViewQuanLy().getPCChamDiem());
                case TaiKhoan -> borderPane.setRight(Singleton.getInstant().getViewQuanLy().getTaiKhoan());
            }
        });
    }

    private void addListenerThongKe(){
        Singleton.getInstant().getViewThongKe().getThongKeProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case TK_DiemSV_MH -> borderPane.setRight(Singleton.getInstant().getViewThongKe().getTK_DiemSV_MH());
                case TK_DiemLop_MonHoc -> borderPane.setRight(Singleton.getInstant().getViewThongKe().getTK_DiemLop_MonHoc());
                case TK_DiemLop_HocKy -> borderPane.setRight(Singleton.getInstant().getViewThongKe().getTK_DiemLop_HocKy());
                case TK_DiemLop_NamHoc -> borderPane.setRight(Singleton.getInstant().getViewThongKe().getTK_DiemLop_NamHoc());
            }
        });
    }

    private void addListenerDanhGia(){
        Singleton.getInstant().getViewDanhGia().getDanhGiaProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case DSHocBong -> borderPane.setRight(Singleton.getInstant().getViewDanhGia().getDSHocBong());
                case DSKhoaLuan -> borderPane.setRight(Singleton.getInstant().getViewDanhGia().getDSKhoaLuan());
            }
        });
    }
    
    public void addListenerChoiceBoc_Nam(){
        Singleton.getInstant().getViewFactory().getNamHoc().set(NamHoc.getRepository().getMa(ChoiceBox_Nam.getValue()));
    }

    public void addListenerChoiceBoc_HocKy(){
        Singleton.getInstant().getViewFactory().getHocky().set(HocKy.getRepository().getMa(ChoiceBox_HocKy.getValue()));
    }
}