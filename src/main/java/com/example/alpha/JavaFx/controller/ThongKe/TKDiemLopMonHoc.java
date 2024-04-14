package com.example.alpha.JavaFx.controller.ThongKe;

import com.example.alpha.JavaFx.controller.setTable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class TKDiemLopMonHoc implements Initializable, setTable {

    @FXML
    private ChoiceBox<String> ChoiceBox_Lop;

    @FXML
    private ChoiceBox<String> ChoiceBox_MonHoc;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Float> Colum_DiemQT;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Float> Colum_DiemTK;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Float> Colum_DiemThi;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Colum_TenSV;

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, String> Coulum_MaSV;

    @FXML
    private TableView<KqSinhVienMonhocEntity> TableView_TKDiemLop;

    private ObservableList<KqSinhVienMonhocEntity> data = FXCollections.observableArrayList();

    private FilteredList<KqSinhVienMonhocEntity> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        setTableView();

        ChoiceBox_Lop.setItems(FXCollections.observableArrayList(PhanLop.getRepository().getAllLop()));
        ChoiceBox_MonHoc.setItems(FXCollections.observableArrayList(MonHoc.getRepository().getAllMonHoc()));
        filteredList = new FilteredList<>(data, b -> true);

        ChoiceBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    ChoiceBox_MonHoc.getValue().equals(kq.getMaMonHoc()));
            TableView_TKDiemLop.setItems(filteredList);
            filteredList.forEach(System.out::println);
            System.out.println(newValue);
        });

        ChoiceBox_MonHoc.valueProperty().addListener((observable, oldValue, newValue) -> {
            AtomicBoolean dk = new AtomicBoolean(false);
            filteredList.setPredicate(kq -> {
                DKHocPhan.getRepository().getMonhocDK(kq.getMaSinhVien()).forEach(DkHP -> {
                    dk.set(DkHP.equals(newValue) && DkHP.equals(kq.getMaMonHoc()));
                });
                return dk.get() && PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ChoiceBox_Lop.getValue()0);
            });
            TableView_TKDiemLop.setItems(filteredList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        data = FXCollections.observableArrayList(KqMonHoc_SV.getRepository().findAll());
        TableView_TKDiemLop.setItems(data);
    }

    @Override
    public void setCellColumn() {
        Coulum_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Colum_DiemTK.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDiemTK()));
        Colum_DiemQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDiemQuaTrinh()));
        Colum_DiemThi.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDiemThi()));
        Colum_TenSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
