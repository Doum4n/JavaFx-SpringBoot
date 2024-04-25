package com.example.alpha.JavaFx.role_admin.controller.ThongKe;

import com.example.alpha.JavaFx.role_admin.model.Lop;
import com.example.alpha.JavaFx.role_admin.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.PhanLop;
import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemQuaTrinh.DiemQTController;
import com.example.alpha.JavaFx.role_student.model.*;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.*;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class TKDiemLopMonHoc implements Initializable, setTable {

    @FXML
    private Label Label_TenMH;


    @FXML
    private Label Label_SiSo;

    @FXML
    private ComboBox<String> ComboBox_Lop;

    @FXML
    private ChoiceBox<String> ChoiceBox_MonHoc;

    @FXML
    private TableColumn<Object, Float> Colum_DiemQT;

    @FXML
    private TableColumn<Object, Float> Colum_DiemTK;

    @FXML
    private TableColumn<Object, Float> Colum_DiemThi;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Colum_TenSV;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Coulum_MaSV;

    @FXML
    private TableView<KqSinhVienMonhocEntity> TableView_TKDiemLop;

    private FilteredList<KqSinhVienMonhocEntity> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        setTableView();
        addListenerComboBox_Lop();

        ChoiceBox_MonHoc.setItems(FXCollections.observableArrayList(MonHoc.getRepository().getAllMonHoc()));
        ChoiceBox_MonHoc.setValue(MonHoc.getRepository().findAll().get(0).toString());

        ChoiceBox_MonHoc.valueProperty().addListener((observable, oldValue, newValue) -> {
            Label_TenMH.setText(MonHoc.getRepository().getTenMH(newValue));
            filteredList.setPredicate(kq -> {
                AtomicBoolean dk = new AtomicBoolean(false);
                List<String> DkHP = DKHocPhan.getRepository().getMonhocDK(kq.getMaSinhVien());
                for(int i=0;i<DKHocPhan.getRepository().getMonhocDK(kq.getMaSinhVien()).size();i++){
                    if(DkHP.get(i).equals(newValue) && DkHP.get(i).equals(kq.getMaMonHoc())){
                        dk.set(true);
                        break;
                    }
                }
                return dk.get() && PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ComboBox_Lop.getValue());
            });
            Label_SiSo.setText(String.valueOf(filteredList.size()));
            TableView_TKDiemLop.setItems(filteredList);
        });

        Singleton.getInstant().getThongKe().getSearch_SV().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                filteredList.setPredicate(kq -> kq.getMaSinhVien().equals(newValue) &&
                        PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ComboBox_Lop.getValue()) &&
                        kq.getMaMonHoc().equals(ChoiceBox_MonHoc.getValue()));
                TableView_TKDiemLop.setItems(filteredList);
            }else {
                filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ComboBox_Lop.getValue()) &&
                        ChoiceBox_MonHoc.getValue().equals(kq.getMaMonHoc()));
                TableView_TKDiemLop.setItems(filteredList);
            }
        });

        Singleton.getInstant().getThongKe().getSearch_Lop().addListener((observable, oldValue, newValue) -> {
            ComboBox_Lop.setValue(newValue);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        ObservableList<KqSinhVienMonhocEntity> data = FXCollections.observableArrayList(KqMonHoc_SV.getRepository().findAll());
        filteredList = new FilteredList<>(data, b -> true);
        TableView_TKDiemLop.setItems(data);
    }

    @Override
    public void setCellColumn() {
        Coulum_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Colum_DiemTK.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(((KqSinhVienMonhocEntity)param.getValue()).getDiemTK()));
        Colum_DiemTK.setCellFactory(new DiemQTController.NullHandlingCellFactory());
        Colum_DiemQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(((KqSinhVienMonhocEntity)param.getValue()).getDiemQuaTrinh()));
        Colum_DiemQT.setCellFactory(new DiemQTController.NullHandlingCellFactory());
        Colum_DiemThi.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(((KqSinhVienMonhocEntity)param.getValue()).getDiemThi()));
        Colum_DiemThi.setCellFactory(new DiemQTController.NullHandlingCellFactory());
        Colum_TenSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }

    private void addListenerComboBox_Lop(){
        ComboBox_Lop.setEditable(true);
        ComboBox_Lop.setItems(FXCollections.observableArrayList(Lop.getRepository().getAllLop()));
        ComboBox_Lop.setValue(PhanLop.getRepository().findAll().get(0).toString());

        ComboBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    ChoiceBox_MonHoc.getValue().equals(kq.getMaMonHoc()));
            TableView_TKDiemLop.setItems(filteredList);
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
}
