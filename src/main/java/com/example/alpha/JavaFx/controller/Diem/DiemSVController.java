package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.Spring_boot.result.student.KqSVMonHoc;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

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
    private TableColumn<Object, Float> Column_DiemQT;

    @FXML
    private TableColumn<Object, Float> Column_DiemTK;

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

        Model.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {
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
                if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(Model.getInstant().getDiemSinhVien().getSvSelected().get())) {
                    MH.add(diemEntity.getMaMonHoc());
                    SV.add(diemEntity.getMaSinhVien());
                    return diemEntity.getMaHocKy().equals(newValue)
                            && diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get())
                            && diemEntity.getMaSinhVien().equals(Model.getInstant().getDiemSinhVien().getSvSelected().get());
                }
                return false;
            });
            MH.clear();
            TableView_DiemSV.setItems(filteredList);
        });

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> {
                if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(Model.getInstant().getDiemSinhVien().getSvSelected().get())) {
                    MH.add(diemEntity.getMaMonHoc());
                    SV.add(diemEntity.getMaSinhVien());
                    return diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get())
                            && diemEntity.getMaNamHoc().equals(newValue)
                            && diemEntity.getMaSinhVien().equals(Model.getInstant().getDiemSinhVien().getSvSelected().get());
                }
                return false;
            });
            MH.clear();
            TableView_DiemSV.setItems(filteredList);
        });

        Model.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {

            double TongDiemTK = 0;
            double TongTC = 0;
            for(int i=0;i<TableView_DiemSV.getItems().size();i++) {

                TongDiemTK += Double.parseDouble(TableView_DiemSV.getColumns().get(6).getCellData(i).toString());
                TongTC += Double.parseDouble(TableView_DiemSV.getColumns().get(2).getCellData(i).toString());
            }
            Model.getInstant().getDiemSinhVien().getDiemTK().set(Math.round(TongDiemTK/TongTC * 100.0) / 100.0);
            Model.getInstant().getDiemSinhVien().getSoTC().set(TongTC);
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
        Column_DiemQT.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(KqMonHoc_SV.getRepository().getDiemQT(((DiemEntity)param.getValue()).getMaSinhVien(),((DiemEntity)param.getValue()).getMaMonHoc())));
        Column_DiemQT.setCellFactory(new DiemQTController.NullHandlingCellFactory());

        Column_DiemTK.setCellValueFactory(param -> {
            List<Float> diems = Diem.getRepository().getDiems(((DiemEntity)param.getValue()).getMaSinhVien(),((DiemEntity)param.getValue()).getMaMonHoc());
            float max = Collections.max(diems);

            //Lấy điểm thi lớn nhất lưu vào database
//            Model.getInstant().getDiemSinhVien().getDiemThi().set(max);
            if(KqMonHoc_SV.getRepository().getDiemThi(((DiemEntity) param.getValue()).getMaSinhVien(),((DiemEntity) param.getValue()).getMaMonHoc())==null){
                KqMonHoc_SV.getRepository().UpdateDiemThi(((DiemEntity) param.getValue()).getMaSinhVien(),((DiemEntity) param.getValue()).getMaMonHoc(),max);
            }

            float TyLe = MonHoc.getRepository().getTyLeDiemQT(((DiemEntity) param.getValue()).getMaMonHoc());
            Float diemqt = (Float) param.getTableView().getColumns().get(4).getCellData(param.getTableView().getItems().indexOf(param.getValue()));
            Float DiemTongKet = diemqt * (TyLe / 100) + max *  ((100 - TyLe) / 100);

            if(KqMonHoc_SV.getRepository().getDiemTK(((DiemEntity) param.getValue()).getMaSinhVien(), ((DiemEntity) param.getValue()).getMaMonHoc())==null){
                KqMonHoc_SV.getRepository().UpdateDiemTK(((DiemEntity) param.getValue()).getMaSinhVien(),((DiemEntity) param.getValue()).getMaMonHoc(), DiemTongKet);
            }
            return new ReadOnlyObjectWrapper<>(Math.round(DiemTongKet * 100.0f) / 100.0f);
        });
        Column_DiemTK.setCellFactory(new DiemQTController.NullHandlingCellFactory());
        Column_STC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(MonHoc.getRepository().getSTC(param.getValue().getMaMonHoc())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
