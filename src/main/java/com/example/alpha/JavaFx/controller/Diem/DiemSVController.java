package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Diem.DiemQT;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.net.URL;
import java.util.*;

@Controller
public class DiemSVController implements Initializable, setTable {
    @FXML
    private TableColumn<DiemEntity, Integer> Column_STC;

    @FXML
    private TableColumn<DiemEntity, Integer> Column_TLQT;

    @FXML
    private TableColumn<DiemEntity, Integer> Column_TLthi;

    @FXML
    private TableColumn<DiemEntity, Double> Column_DiemQT;

    @FXML
    private TableColumn<DiemEntity, Double> Column_DiemTK;

    @FXML
    private TableColumn<DiemEntity, String> Column_Loai;

    @FXML
    private TableColumn<DiemEntity, String> Column_MaMH;

    @FXML
    private TableColumn<DiemEntity, String> Column_TenMH;

    @FXML
    private TableColumn<DiemEntity, Double> Column_ThiL1;

    @FXML
    private TableColumn<DiemEntity, Double> Column_ThiL2;

    @FXML
    private TableColumn<DiemEntity, Double> Column_ThiL3;

    @FXML
    private TableView<DiemEntity> TableView_DiemSV;

    private ObservableList<DiemEntity> data = FXCollections.observableArrayList();

    private FilteredList<DiemEntity> filteredList;

    private final ListProperty<String> MH = new SimpleListProperty<>(FXCollections.observableArrayList(""));
    private final ListProperty<String> SV = new SimpleListProperty<>(FXCollections.observableArrayList(""));

    private final DoubleProperty MaxDiemThi = new SimpleDoubleProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        filteredList.forEach(diemEntity -> {
            Double diem = Diem.getRepository().getDiem(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc(), String.valueOf(diemEntity.getLanThi()));
            if(diem!=null){
                List<String> LanThi = Diem.getRepository().getLanThi(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc());
                LanThi.forEach(s -> {
                    switch (s) {
                        case "1": {
                            Column_ThiL1.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(Diem.getRepository().getDiem(param.getValue().getMaSinhVien(), param.getValue().getMaMonHoc(), "1")));
                            break;
                        }
                        case "2": {
                            Column_ThiL2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(Diem.getRepository().getDiem(param.getValue().getMaSinhVien(), param.getValue().getMaMonHoc(), "2")));
                            break;
                        }
                        case "3":
                            Column_ThiL3.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(Diem.getRepository().getDiem(param.getValue().getMaSinhVien(), param.getValue().getMaMonHoc(), "3")));
                            break;
                    }
                });
            }
        });

        Model.getInstant().getViewQuanLy().getSvSelected().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> {
                if(!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(newValue)){
                    MH.add(diemEntity.getMaMonHoc());
                    SV.add(diemEntity.getMaSinhVien());
                    return diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get())
                            && diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get());
                }
                return false;
            });
            MH.clear();
            TableView_DiemSV.setItems(filteredList);
        });

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> {
                if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(Model.getInstant().getViewQuanLy().getSvSelected().get())) {
                    MH.add(diemEntity.getMaMonHoc());
                    SV.add(diemEntity.getMaSinhVien());
                    return diemEntity.getMaHocKy().equals(newValue)
                            && diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get())
                            && diemEntity.getMaSinhVien().equals(Model.getInstant().getViewQuanLy().getSvSelected().get());
                }
                return false;
            });
            MH.clear();
            TableView_DiemSV.setItems(filteredList);
        });

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> {
                if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(Model.getInstant().getViewQuanLy().getSvSelected().get())) {
                    MH.add(diemEntity.getMaMonHoc());
                    SV.add(diemEntity.getMaSinhVien());
                    return diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get())
                            && diemEntity.getMaNamHoc().equals(newValue)
                            && diemEntity.getMaSinhVien().equals(Model.getInstant().getViewQuanLy().getSvSelected().get());
                }
                return false;
            });
            MH.clear();
            TableView_DiemSV.setItems(filteredList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<DiemEntity> list = Diem.getRepository().findAll();

        data = FXCollections.observableArrayList(list);

        filteredList = new FilteredList<>(data,b -> true);

        filteredList.setPredicate(diemEntity -> {
            if(!MH.get().contains(diemEntity.getMaMonHoc())){
                MH.add(diemEntity.getMaMonHoc());
                return true;
            }
            return false;
        });
        MH.clear();

        TableView_DiemSV.setItems(filteredList);
    }

    @Override
    public void setCellColumn() {
        Column_MaMH.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaMonHoc()));
        Column_TenMH.setCellValueFactory(param -> new SimpleStringProperty(MonHoc.getRepository().getTenMH(param.getValue().getMaMonHoc())));
        Column_TLQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(MonHoc.getRepository().getTyLeDiemQT(param.getValue().getMaMonHoc())));
        Column_TLthi.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(100-MonHoc.getRepository().getTyLeDiemQT(param.getValue().getMaMonHoc())));
        Column_DiemQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(DiemQT.getRepository().getDiemQT(param.getValue().getMaSinhVien(),param.getValue().getMaMonHoc())));
        Column_DiemTK.setCellValueFactory(param -> {
            List<Double> diems = Diem.getRepository().getDiems(param.getValue().getMaSinhVien(),param.getValue().getMaMonHoc());
            Double max = Collections.max(diems);
            double TyLe = MonHoc.getRepository().getTyLeDiemQT(param.getValue().getMaMonHoc());
            Double diemqt = (Double) param.getTableView().getColumns().get(4).getCellData(param.getTableView().getItems().indexOf(param.getValue()));
//            System.out.println(param.getTableView().getColumns().get(4).getCellData(param.getTableView().getItems().indexOf(param.getValue())));
            return new ReadOnlyObjectWrapper<>(diemqt*(Double)(TyLe/100) + max* ((100-TyLe) /100));
        });
        Column_STC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(MonHoc.getRepository().getSTC(param.getValue().getMaMonHoc())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
