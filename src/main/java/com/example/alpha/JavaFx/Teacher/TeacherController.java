package com.example.alpha.JavaFx.Teacher;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.GiaoVien.GiaoVien;
import com.example.alpha.JavaFx.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSVMonHoc;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Field;
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
    private TextField textField_DiemQT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        addListenerTableView();

        ChoiceBox_Lop.setItems(FXCollections.observableArrayList(PhanCong.getRepository().getLopByMaGV("1", "1", "2024")));
        ChoiceBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    kq.getMaMonHoc().equals(PhanCong.getRepository().getMonHoc("1", "1","2024")));
            TableView_DiemQT.setItems(filteredList);
        });

        Label_MaGV.setText("1");
        Label_TenGV.setText(GiaoVien.getRepository().getTenGV("1"));

        Buton_Update.setOnAction(event -> {
            KqMonHoc_SV.getRepository().updateDiem(Label_MaSV.getText(),
                    PhanCong.getRepository().getMonHoc("1", "1","2024"),
                    Float.valueOf(textField_DiemQT.getText()));
            filteredList = new FilteredList<>(FXCollections.observableArrayList(KqMonHoc_SV.getRepository().findAll()));

            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ChoiceBox_Lop.getValue()) &&
                    kq.getMaMonHoc().equals(PhanCong.getRepository().getMonHoc("1", "1","2024")));
            TableView_DiemQT.setItems(filteredList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(KqMonHoc_SV.getRepository().findAll()));
        filteredList.setPredicate(kq -> kq.getMaMonHoc().equals(PhanCong.getRepository().getMonHoc("1", "1", "2024")) &&
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
        TableView_DiemQT.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            TableView_DiemQT.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(TableView_DiemQT.getSelectionModel().getSelectedIndex()  > -1) {
                    KqSinhVienMonhocEntity diemQT = filteredList.get(TableView_DiemQT.getSelectionModel().getSelectedIndex());
                    Label_MaSV.setText(diemQT.getMaSinhVien());
                    Label_TenSV.setText(SinhVien.getRepository().getByHoTen(diemQT.getMaSinhVien()));
                    textField_DiemQT.setText(KqMonHoc_SV.getRepository().getDiemQT(diemQT.getMaSinhVien(), diemQT.getMaMonHoc()).toString());
                }
            });
        });
    }

    @Override
    public void addListenerSearch() {

    }
}
