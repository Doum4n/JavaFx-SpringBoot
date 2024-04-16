package com.example.alpha.JavaFx.controller.ThongKe;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.model.Diem.PhongThi;
import com.example.alpha.JavaFx.model.Lop;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinnhVienHocKy;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.persistence.Column;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TKDiemLopHocKy implements Initializable, setTable {

    @FXML
    private ChoiceBox<String> ChoiceBox_Lop;

    @FXML
    private ComboBox<String> ComboBox_Lop;

    @FXML
    private TableColumn<KqSinnhVienHocKy, Float> Colum_DiemTK;

    @FXML
    private TableColumn<KqSinnhVienHocKy, String> Colum_TenSV;

    @FXML
    private TableColumn<KqSinnhVienHocKy, String> Colum_MaSV;

    @FXML
    private TableColumn<KqSinnhVienHocKy, Integer> Column_SoTC;

    @FXML
    private Label Label_SiSo;

    @FXML
    private TableView<KqSinnhVienHocKy> TableView_TKDiemLopHK;

    private FilteredList<KqSinnhVienHocKy> filteredList;

    private ObservableList<KqSinnhVienHocKy> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        ComboBox_Lop.setEditable(true);
        ComboBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ComboBox_Lop.getValue()));
            Label_SiSo.setText(String.valueOf(filteredList.size()));
            TableView_TKDiemLopHK.setItems(filteredList);
        });
        ComboBox_Lop.getEditor().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            var suggestion = FXCollections.observableArrayList(Lop.getRepository().getAllLop());

            ObservableList<String> S = FXCollections.observableArrayList();
            for (String s : suggestion) {
                if (!s.equals(ComboBox_Lop.getEditor().getText())) {
                    if (s.contains(ComboBox_Lop.getEditor().getText())) {
                        S.add(s);
                    }
                }
            }
            if(!event.getCode().equals(KeyCode.ENTER)) {
                ComboBox_Lop.setItems(S);
                ComboBox_Lop.show();
            }
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        data = FXCollections.observableArrayList(DiemSV_HocKy.getRepository().findAll());
        filteredList = new FilteredList<>(data);
        TableView_TKDiemLopHK.setItems(data);
    }

    @Override
    public void setCellColumn() {
        Colum_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Colum_TenSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
        Colum_DiemTK.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDiemTkHocKy()));
        Column_SoTC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDoTC()));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
