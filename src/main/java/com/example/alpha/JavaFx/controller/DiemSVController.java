package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Diem;
import com.example.alpha.JavaFx.model.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.MonHoc;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.student.DKHocPhanEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class DiemSVController implements Initializable, setTable{
    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Double> Column_DiemQT;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Double> Column_DiemTK;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Column_Loai;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Column_MaMH;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Column_TenMH;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Integer> Column_ThiL1;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Integer> Column_ThiL2;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Integer> Column_ThiL3;

    @FXML
    private TableView<KqSinhVienMonhocEntity> TableView_DiemSV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
    }

    @Override
    public void setTableView() {
        List<KqSinhVienMonhocEntity> list = KqMonHoc_SV.getRepository().findAll();
        TableView_DiemSV.setItems(FXCollections.observableArrayList(list));
    }

    @Override
    public void setCellColumn() {
        Column_DiemQT.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDiemQuaTrinh()));
//        Column_DiemTK.setCellValueFactory();
//        Column_Loai;
        Column_MaMH.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaMonHoc()));
        Column_TenMH.setCellValueFactory(param -> new SimpleStringProperty(MonHoc.getRepository().getTenMH(param.getValue().getMaMonHoc())));
        Column_ThiL1.setCellValueFactory(param -> {
            System.out.println(Diem.getRepository().getLanThi(param.getValue().getMaSinhVien()));
            return new SimpleObjectProperty<>(Diem.getRepository().getLanThi(param.getValue().getMaSinhVien()).get(0));
        });
//        Column_ThiL1;
//        Column_ThiL2;
//        Column_ThiL3;
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
