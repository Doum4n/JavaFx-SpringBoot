package com.example.alpha.JavaFx.role_teacher;

import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.role_admin.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.role_admin.model.PhanLop;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.*;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable, setTable {
    @FXML
    private Button Buton_Update;

    @FXML
    private ChoiceBox<String> ChoiceBox_Lop;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Float> Column_DiemQT;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Column_MaSV;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Column_TenSV;

    @FXML
    private TableView<KqSinhVienMonhocEntity> TableView_DiemQT;

    private FilteredList<KqSinhVienMonhocEntity> filteredList;

    @FXML
    private Label Label_MaGV;

    @FXML
    private Label Label_TenGV;

    @FXML
    private Label Label_MaSV;

    @FXML
    private Label Label_TenSV;

    @FXML
    private Button button_logOut;

    @FXML
    private TextField textField_DiemQT;

    @FXML
    private Button Button_edit;

    String id = Singleton.getInstant().getViewFactory().getUsername().get();
    String hocky = Singleton.getInstant().getViewFactory().getHocky().get();
    String namhoc = Singleton.getInstant().getViewFactory().getNamHoc().get();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        addListenerTableView();

        ChoiceBox_Lop.setItems(FXCollections.observableArrayList(PhanCong.getRepository().getLopByMaGV(id, hocky, namhoc)));
        ChoiceBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    kq.getMaMonHoc().equals(PhanCong.getRepository().getMonHoc(id, hocky, namhoc)));
            TableView_DiemQT.setItems(filteredList);
        });

        Label_MaGV.setText(id);
        Label_TenGV.setText(GiaoVien.getRepository().getTenGV(id));

        Buton_Update.setOnAction(event -> {
            KqMonHoc_SV.getRepository().updateDiem(Label_MaSV.getText(),
                    PhanCong.getRepository().getMonHoc(id, hocky, namhoc),
                    Float.valueOf(textField_DiemQT.getText()),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
            filteredList = new FilteredList<>(FXCollections.observableArrayList(KqMonHoc_SV.getRepository().findAll()));

            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ChoiceBox_Lop.getValue()) &&
                    kq.getMaMonHoc().equals(PhanCong.getRepository().getMonHoc(id, hocky, namhoc)));
            TableView_DiemQT.setItems(filteredList);
        });

        button_logOut.setOnAction(event -> {
            Singleton.getInstant().getViewFactory().showLoginWindow();
        });

        Button_edit.setOnAction(event -> {
            Singleton.getInstant().getViewFactory().showChangePasswordWindow();
        });

        Label_TenSV.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Label_MaSV.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Label_MaGV.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Label_TenSV.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(KqMonHoc_SV.getRepository().findAll()));
        filteredList.setPredicate(kq -> kq.getMaMonHoc().equals(PhanCong.getRepository().getMonHoc(id, hocky, namhoc)) &&
                PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ChoiceBox_Lop.getValue()));
        TableView_DiemQT.setItems(filteredList);
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Column_TenSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
        Column_DiemQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDiemQuaTrinh()));
    }

    @Override
    public void addListenerTableView() {
//        TableView_DiemQT.setSelectionModel(TableView_DiemQT.getSelectionModel());
        TableView_DiemQT.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
//                TableView_DiemQT.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (TableView_DiemQT.getSelectionModel().getSelectedIndex() > -1) {
                    KqSinhVienMonhocEntity diemQT = filteredList.get(TableView_DiemQT.getSelectionModel().getSelectedIndex());
                    Label_MaSV.setText(diemQT.getMaSinhVien());
                    Label_TenSV.setText(SinhVien.getRepository().getByHoTen(diemQT.getMaSinhVien()));
                    textField_DiemQT.setText(KqMonHoc_SV.getRepository().getDiemQT(diemQT.getMaSinhVien(), diemQT.getMaMonHoc()).toString());
                }
//                });
            }
        });
    }

    @Override
    public void addListenerSearch() {

    }
}
