package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.Load.LoadScence;
import com.example.alpha.JavaFx.model.HocKy;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.NamHoc;
import com.example.alpha.JavaFx.view.Menu;
import com.example.alpha.repository.HockyEntityRepository;
import com.example.alpha.repository.NamhocEntityRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.alpha.JavaFx.view.Menu.QuanLy;

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
                    borderPane.setRight(Model.getInstant().getViewQuanLy().getQLSinhVien());
                }
                case Diem -> borderPane.setRight(Model.getInstant().getViewQuanLy().getDiem());
//                case PhanLop -> borderPane.setRight(Model.getInstant().getViewQuanLy().getPhanLop());
                case QLGiaoVien -> borderPane.setRight(Model.getInstant().getViewQuanLy().getQLGiaoVien());
                case PhanCong -> borderPane.setRight(Model.getInstant().getViewQuanLy().getScrollPane());
                case TaiKhoan -> borderPane.setRight(Model.getInstant().getViewQuanLy().getTaiKhoan());
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