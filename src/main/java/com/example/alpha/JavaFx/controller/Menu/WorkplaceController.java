package com.example.alpha.JavaFx.controller.Menu;

import com.example.alpha.JavaFx.Load.LoadScence;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.model.NamHoc;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.view.Menu;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import com.example.alpha.repository.HockyEntityRepository;
import com.example.alpha.repository.NamhocEntityRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.alpha.JavaFx.view.Menu.QuanLy;
import static com.example.alpha.JavaFx.view.Menu.ThongKe;

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
                case TongQuan -> {
                    AnchorPane_HBox.getChildren().clear();
                    borderPane.setRight(Model.getInstant().getViewFactory().getDashboard());
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
                case QLSinhVien -> {
//                    borderPane.setRight(Model.getInstant().getViewQuanLy().getQLSinhVien());
                }
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
    
    public void addListenerChoiceBoc_Nam(){
        Model.getInstant().getViewFactory().getNamHoc().set(NamHoc.getRepository().getMa(ChoiceBox_Nam.getValue()));
    }

    public void addListenerChoiceBoc_HocKy(){
        Model.getInstant().getViewFactory().getHocky().set(HocKy.getRepository().getMa(ChoiceBox_HocKy.getValue()));
    }
}