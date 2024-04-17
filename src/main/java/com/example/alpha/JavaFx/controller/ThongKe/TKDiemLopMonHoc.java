package com.example.alpha.JavaFx.controller.ThongKe;

import com.example.alpha.JavaFx.controller.Diem.DiemQTController;
import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Lop;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien.DKHocPhan;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private ChoiceBox<String> ChoiceBox_Lop;

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

        ChoiceBox_Lop.setItems(FXCollections.observableArrayList(Lop.getRepository().getAllLop()));
        ChoiceBox_Lop.setValue(PhanLop.getRepository().findAll().get(0).toString());
        ChoiceBox_MonHoc.setItems(FXCollections.observableArrayList(MonHoc.getRepository().getAllMonHoc()));
        ChoiceBox_MonHoc.setValue(MonHoc.getRepository().findAll().get(0).toString());

        ChoiceBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    ChoiceBox_MonHoc.getValue().equals(kq.getMaMonHoc()));
            TableView_TKDiemLop.setItems(filteredList);
            filteredList.forEach(System.out::println);
            System.out.println(newValue);
        });

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
                return dk.get() && PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ChoiceBox_Lop.getValue());
            });
            Label_SiSo.setText(String.valueOf(filteredList.size()));
            TableView_TKDiemLop.setItems(filteredList);
            System.out.println(newValue);
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
}
