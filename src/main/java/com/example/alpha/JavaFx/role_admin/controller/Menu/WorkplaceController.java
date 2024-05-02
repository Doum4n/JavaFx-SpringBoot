package com.example.alpha.JavaFx.role_admin.controller.Menu;

import com.example.alpha.JavaFx.role_admin.model.HocKy;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.NamHoc;
import com.example.alpha.JavaFx.role_admin.model.Status;
import javafx.application.Platform;
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
import java.text.SimpleDateFormat;
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
    private ChoiceBox<String> ChoiceBox_TienTrinh;

    @FXML
    private Label Label_time;

    @FXML
    private HBox HBox_Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ChoiceBox_Nam.setItems(FXCollections.observableArrayList((NamHoc.getRepository().getNamHoc())));
        ChoiceBox_HocKy.setItems(FXCollections.observableArrayList((HocKy.getRepository().getHocKy())));

        Singleton.getInstant().getViewFactory().getMenuProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case NhapDiem -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Singleton.getInstant().getViewFactory().getQuanLyView());
                    }
                    addListenerQuanLy();
                }
                case DanhGia -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Singleton.getInstant().getViewFactory().getDanhGia());
                    }
                    addListenerDanhGia();
                }
                case ThongKe -> {
                    if(!Objects.equals(oldValue, newValue)) {
                        AnchorPane_HBox.getChildren().clear();
                        AnchorPane_HBox.getChildren().add(Singleton.getInstant().getViewFactory().getThongKe());
                    }
                    addListenerThongKe();
                }
                case TaiKhoan -> {
                    AnchorPane_HBox.getChildren().clear();
                    borderPane.setRight(Singleton.getInstant().getViewFactory().getQLTaiKhoan());
                }
            }
        });
        ChoiceBox_HocKy.setValue(HocKy.getRepository().getHocKy().get(0));
        ChoiceBox_Nam.setValue(NamHoc.getRepository().getNamHoc().get(0));

        addListenerChoiceBoc_Nam();
        addListenerChoiceBoc_HocKy();

        ChoiceBox_Nam.valueProperty().addListener((observable, oldValue, newValue) -> addListenerChoiceBoc_Nam());
        ChoiceBox_HocKy.valueProperty().addListener((observable, oldValue, newValue) -> addListenerChoiceBoc_HocKy());

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> Label_time.setText(getCurrentTime()));
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);


        ChoiceBox_TienTrinh.setItems(FXCollections.observableArrayList("Nhập điểm", "Rà soát", "Thống kê"));
        ChoiceBox_TienTrinh.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Nhập điểm"))
                Singleton.getInstant().getViewFactory().getTienTrinh().set(TienTrinh.input_score);
            else if (newValue.equals("Rà soát")) {
                Singleton.getInstant().getViewFactory().getTienTrinh().set(TienTrinh.review);
            }else {
                Singleton.getInstant().getViewFactory().getTienTrinh().set(TienTrinh.statistical);
            }
        });
    }

    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(Calendar.getInstance().getTime());
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