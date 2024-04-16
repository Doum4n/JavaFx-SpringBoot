package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.model.HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.NamHoc;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.springframework.stereotype.Controller;

import java.net.URL;
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
    private HBox HBox_Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ChoiceBox_Nam.setItems(FXCollections.observableArrayList((NamHoc.getRepository().getNamHoc())));
        ChoiceBox_HocKy.setItems(FXCollections.observableArrayList((HocKy.getRepository().getHocKy())));

        Model.getInstant().getViewFactory().getMenuProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case QuanLy -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Model.getInstant().getViewFactory().getQuanLyView());
                    }
                    addListenerQuanLy();
                }
                case DanhGia -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Model.getInstant().getViewFactory().getDanhGia());
                    }
                    addListenerDanhGia();
                }
                case ThongKe -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Model.getInstant().getViewFactory().getThongKe());
                    }
                    addListenerThongKe();
                }
            }
        });
        ChoiceBox_HocKy.setValue(HocKy.getRepository().getHocKy().get(0));
        ChoiceBox_Nam.setValue(NamHoc.getRepository().getNamHoc().get(0));

        addListenerChoiceBoc_Nam();
        addListenerChoiceBoc_HocKy();

        ChoiceBox_Nam.valueProperty().addListener((observable, oldValue, newValue) -> addListenerChoiceBoc_Nam());
        ChoiceBox_HocKy.valueProperty().addListener((observable, oldValue, newValue) -> addListenerChoiceBoc_HocKy());
    }

    private void addListenerQuanLy(){
        Model.getInstant().getViewQuanLy().getQuanLyProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case DiemThi -> borderPane.setRight(Model.getInstant().getViewQuanLy().getDiem());
                case DiemQT -> borderPane.setRight(Model.getInstant().getViewQuanLy().getPCChamDiem());
                case TaiKhoan -> borderPane.setRight(Model.getInstant().getViewQuanLy().getTaiKhoan());
            }
        });
    }

    private void addListenerThongKe(){
        Model.getInstant().getViewThongKe().getThongKeProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case TK_DiemSV_MH -> borderPane.setRight(Model.getInstant().getViewThongKe().getTK_DiemSV_MH());
                case TK_DiemLop_MonHoc -> borderPane.setRight(Model.getInstant().getViewThongKe().getTK_DiemLop_MonHoc());
                case TK_DiemLop_HocKy -> borderPane.setRight(Model.getInstant().getViewThongKe().getTK_DiemLop_HocKy());
            }
        });
    }

    private void addListenerDanhGia(){
        Model.getInstant().getViewDanhGia().getDanhGiaProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case DSHocBong -> borderPane.setRight(Model.getInstant().getViewDanhGia().getDSHocBong());
                case DSKhoaLuan -> borderPane.setRight(Model.getInstant().getViewDanhGia().getDSKhoaLuan());
            }
        });
    }
    
    public void addListenerChoiceBoc_Nam(){
        Model.getInstant().getViewFactory().getNamHoc().set(NamHoc.getRepository().getMa(ChoiceBox_Nam.getValue()));
    }

    public void addListenerChoiceBoc_HocKy(){
        Model.getInstant().getViewFactory().getHocky().set(HocKy.getRepository().getMa(ChoiceBox_HocKy.getValue()));
    }
}