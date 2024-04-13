package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.DiemQT;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DiemQTController implements Initializable, setTable {
    @FXML
    private TableColumn<?, ?> Column_DiemQT;

    @FXML
    private TableColumn<DiemQTEntity, String> Column_MaSV;

    @FXML
    private TableColumn<DiemQTEntity, String> Column_TenSV;

    @FXML
    private TableView<DiemQTEntity> TableView_Diem;

    private ObservableList<DiemQTEntity> data = FXCollections.observableArrayList();

    private FilteredList<DiemQTEntity> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        addListenerTableView();
        addListenerSearch();

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaHK().equals(newValue) &&
                    diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            TableView_Diem.setItems(filteredList);
        });

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaNH().equals(newValue) &&
                    diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()));
            TableView_Diem.setItems(filteredList);
        });

        //Khi nhấn vào xem, cập nhật lớp và môn học
        Model.getInstant().getDiemQuaTrinh().getLopSelected().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                    diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                    Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.getMaSV()), newValue) &&
                    diemQTEntity.getMaMH().equals(Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get()));
            TableView_Diem.setItems(filteredList);
        });

        Model.getInstant().getDiemQuaTrinh().getMaMHSelected().addListener((observable1, oldValue1, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                    diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                    Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.getMaSV()), Model.getInstant().getDiemQuaTrinh().getLopSelected().get()) &&
                    diemQTEntity.getMaMH().equals(newValue));
            TableView_Diem.setItems(filteredList);
        });

        //Khi nhấn update
        Model.getInstant().getDiemQuaTrinh().getIsUpdate().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(true)){
                DiemQTEntity diemQT = new DiemQTEntity(Model.getInstant().getDiemQuaTrinh().getMaSV().get(),
                        Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get(),
                        Model.getInstant().getViewFactory().getHocky().get(),
                        Model.getInstant().getViewFactory().getNamHoc().get(),
                        Double.valueOf(Model.getInstant().getDiemQuaTrinh().getDiem().get()));

                data.removeIf(diemQTEntity -> diemQTEntity.getMaSV().equals(diemQT.getMaSV()) && diemQTEntity.getMaMH().equals(diemQT.getMaMH()));
                data.add(diemQT);

                defaultsLoad();
            }
        });
    }

    private void defaultsLoad() {
        filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.getMaSV()), Model.getInstant().getDiemQuaTrinh().getLopSelected().get()) &&
                diemQTEntity.getMaMH().equals(Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get()));
        TableView_Diem.setItems(filteredList);
    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<DiemQTEntity> diem = DiemQT.getRepository().findAll();
        data = FXCollections.observableArrayList(diem);
        TableView_Diem.setItems(data);
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaSV()));
        Column_TenSV.setCellValueFactory(param -> new SimpleStringProperty(SinhVien.getRepository().getByHoTen(param.getValue().getMaSV())));
        Column_DiemQT.setCellValueFactory(new PropertyValueFactory<>("DiemQT"));
    }

    @Override
    public void addListenerTableView() {
        TableView_Diem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            TableView_Diem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(TableView_Diem.getSelectionModel().getSelectedIndex() > -1) {
                    DiemQTEntity diemQT = filteredList.get(TableView_Diem.getSelectionModel().getSelectedIndex());
                    Model.getInstant().getDiemQuaTrinh().getDiem().set(String.valueOf(diemQT.getDiemQT()));
                    Model.getInstant().getDiemQuaTrinh().getMaSV().set(diemQT.getMaSV());
                }
            });
        });
    }

    @Override
    public void addListenerSearch() {
        //Tìm kiếm gián tiếp thông qua StringProperty Search của DiemQuaTrinh
        Model.getInstant().getDiemQuaTrinh().getSearch().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                filteredList.setPredicate(diemQTEntity -> diemQTEntity.getMaMH().equals(Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get()) &&
                        diemQTEntity.getMaSV().contains(newValue) &&
                        diemQTEntity.getMaHK().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                        diemQTEntity.getMaNH().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                        Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.getMaSV()), Model.getInstant().getDiemQuaTrinh().getLopSelected().get()));
                TableView_Diem.setItems(filteredList);
            }else {
                filteredList = new FilteredList<>(data, b-> true);
                defaultsLoad();
            }
        });
    }
}
