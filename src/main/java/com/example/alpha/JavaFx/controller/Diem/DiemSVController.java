package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.TaiKhoan.AccountType;
import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
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
import java.security.Signature;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        if(Singleton.getInstant().getViewFactory().getType().equals(AccountType.Admin)) {
            addListenerSvSelected();

            Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(diemEntity -> {
                    if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(Singleton.getInstant().getDiemSinhVien().getSvSelected().get())) {
                        MH.add(diemEntity.getMaMonHoc());
                        SV.add(diemEntity.getMaSinhVien());
                        return diemEntity.getMaHocKy().equals(newValue)
                                && diemEntity.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get())
                                && diemEntity.getMaSinhVien().equals(Singleton.getInstant().getDiemSinhVien().getSvSelected().get());
                    }
                    return false;
                });
                MH.clear();
                TableView_DiemSV.setItems(filteredList);
            });

            Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(diemEntity -> {
                    if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(Singleton.getInstant().getDiemSinhVien().getSvSelected().get())) {
                        MH.add(diemEntity.getMaMonHoc());
                        SV.add(diemEntity.getMaSinhVien());
                        return diemEntity.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get())
                                && diemEntity.getMaNamHoc().equals(newValue)
                                && diemEntity.getMaSinhVien().equals(Singleton.getInstant().getDiemSinhVien().getSvSelected().get());
                    }
                    return false;
                });
                MH.clear();
                TableView_DiemSV.setItems(filteredList);
            });


        }else if(Singleton.getInstant().getViewFactory().getType().equals(AccountType.Student)){
            filteredList.forEach(diemEntity -> {
                Double diem = Diem.getRepository().getDiem(diemEntity.getMaSinhVien(),
                        diemEntity.getMaMonHoc(), String.valueOf(diemEntity.getLanThi()),
                        Singleton.getInstant().getDiemSinnVienStudent().getHocKy().get(),
                        Singleton.getInstant().getDiemSinnVienStudent().getNamHoc().get()
                );
                if (diem != null) {
                    List<String> LanThi = Diem.getRepository().getLanThi(diemEntity.getMaSinhVien(), diemEntity.getMaMonHoc());
                    LanThi.forEach(s -> {
                        switch (s) {
                            case "1": {
                                Column_ThiL1.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                                        Diem.getRepository().getDiem(param.getValue().getMaSinhVien(),
                                                param.getValue().getMaMonHoc(),
                                                "1",
                                                Singleton.getInstant().getViewFactory().getHocky().get(),
                                                Singleton.getInstant().getViewFactory().getNamHoc().get()))
                                );
                                break;
                            }
                            case "2": {
                                Column_ThiL2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                                        Diem.getRepository().getDiem(param.getValue().getMaSinhVien(),
                                                param.getValue().getMaMonHoc(),
                                                "2",
                                                Singleton.getInstant().getViewFactory().getHocky().get(),
                                                Singleton.getInstant().getViewFactory().getNamHoc().get()))
                                );
                                break;
                            }
                            case "3":
                                Column_ThiL3.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                                        Diem.getRepository().getDiem(param.getValue().getMaSinhVien(),
                                                param.getValue().getMaMonHoc(),
                                                "3",
                                                Singleton.getInstant().getViewFactory().getHocky().get(),
                                                Singleton.getInstant().getViewFactory().getNamHoc().get()))
                                );
                                break;
                        }
                    });
                }
            });

            filteredList.setPredicate(diemEntity -> diemEntity.getMaHocKy().equals(Singleton.getInstant().getDiemSinnVienStudent().getHocKy().get()) &&
                    diemEntity.getMaNamHoc().equals(Singleton.getInstant().getDiemSinnVienStudent().getNamHoc().get()) &&
                    diemEntity.getMaSinhVien().equals(Singleton.getInstant().getDiemSinnVienStudent().getMaSV().get()));

            TableView_DiemSV.setItems(filteredList);
        }
    }

    @Override
    public void setTableView() {
        if(Singleton.getInstant().getViewFactory().getType().equals(AccountType.Admin)) {
            setCellColumn();
            List<DiemEntity> list = Diem.getRepository().findAll();

            data = FXCollections.observableArrayList(list);

            filteredList = new FilteredList<>(data, b -> true);

            filteredList.setPredicate(diemEntity -> {
                if (!MH.get().contains(diemEntity.getMaMonHoc())) {
                    MH.add(diemEntity.getMaMonHoc());
                    return true;
                }
                return false;
            });
            MH.clear();

            TableView_DiemSV.setItems(filteredList);
        }else if(Singleton.getInstant().getViewFactory().getType().equals(AccountType.Student)) {
            setCellColumn();
            data = FXCollections.observableArrayList(Diem.getRepository().findAllbyMaSV(Singleton.getInstant().getViewFactory().getUsername().get()));
            filteredList = new FilteredList<>(data, b -> true);
            TableView_DiemSV.setItems(filteredList);
        }
    }

    @Override
    public void setCellColumn() {
        Column_MaMH.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaMonHoc()));
        Column_TenMH.setCellValueFactory(param -> new SimpleStringProperty(MonHoc.getRepository().getTenMH(param.getValue().getMaMonHoc())));
        Column_TLQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(MonHoc.getRepository().getTyLeDiemQT(param.getValue().getMaMonHoc())));
        Column_TLthi.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(100-MonHoc.getRepository().getTyLeDiemQT(param.getValue().getMaMonHoc())));
        Column_DiemQT.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(KqMonHoc_SV.getRepository().getDiemQT(((DiemEntity)param.getValue()).getMaSinhVien(),((DiemEntity)param.getValue()).getMaMonHoc())));
        Column_DiemQT.setCellFactory(new DiemQTController.NullHandlingCellFactory());

        Column_DiemTK.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                KqMonHoc_SV.getRepository().getDiemTK(((DiemEntity) param.getValue()).getMaSinhVien(),
                ((DiemEntity) param.getValue()).getMaMonHoc(),
                ((DiemEntity) param.getValue()).getMaHocKy(),
                ((DiemEntity) param.getValue()).getMaNamHoc())
        ));
        Column_DiemTK.setCellFactory(new DiemQTController.NullHandlingCellFactory());
        Column_STC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(MonHoc.getRepository().getSTC(param.getValue().getMaMonHoc())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }

    private void addListenerSvSelected(){
        //Hiển thi bảng điểm
        Singleton.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {
            setTableView();
            filteredList.forEach(diemEntity -> {
                Double diem = Diem.getRepository().getDiem(diemEntity.getMaSinhVien(),
                        diemEntity.getMaMonHoc(), String.valueOf(diemEntity.getLanThi()),
                        Singleton.getInstant().getViewFactory().getHocky().get(),
                        Singleton.getInstant().getViewFactory().getNamHoc().get()
                );
                if (diem != null) {
                    List<String> LanThi = Diem.getRepository().getLanThi(diemEntity.getMaSinhVien(), diemEntity.getMaMonHoc());
                    LanThi.forEach(s -> {
                        switch (s) {
                            case "1": {
                                Column_ThiL1.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                                        Diem.getRepository().getDiem(param.getValue().getMaSinhVien(),
                                                param.getValue().getMaMonHoc(),
                                                "1",
                                                Singleton.getInstant().getViewFactory().getHocky().get(),
                                                Singleton.getInstant().getViewFactory().getNamHoc().get()))
                                );
                                break;
                            }
                            case "2": {
                                Column_ThiL2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                                        Diem.getRepository().getDiem(param.getValue().getMaSinhVien(),
                                                param.getValue().getMaMonHoc(),
                                                "2",
                                                Singleton.getInstant().getViewFactory().getHocky().get(),
                                                Singleton.getInstant().getViewFactory().getNamHoc().get()))
                                );
                                break;
                            }
                            case "3":
                                Column_ThiL3.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                                        Diem.getRepository().getDiem(param.getValue().getMaSinhVien(),
                                                param.getValue().getMaMonHoc(),
                                                "3",
                                                Singleton.getInstant().getViewFactory().getHocky().get(),
                                                Singleton.getInstant().getViewFactory().getNamHoc().get()))
                                );
                                break;
                        }
                    });
                }
            });
        });

        //filter
        Singleton.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> {
                if (!MH.get().contains(diemEntity.getMaMonHoc()) && diemEntity.getMaSinhVien().equals(newValue)) {
                    MH.add(diemEntity.getMaMonHoc());
                    SV.add(diemEntity.getMaSinhVien());
                    return diemEntity.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get())
                            && diemEntity.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get());
                }
                return false;
            });
            MH.clear();
            TableView_DiemSV.setItems(filteredList);
        });

        //Khi DiemTK và TongTC = null
        Singleton.getInstant().getDiemSinhVien().getSvSelected().addListener((observable, oldValue, newValue) -> {
            var diemTK = DiemSV_HocKy.getRepository().getDiemTK(
                    Singleton.getInstant().getDiemSinhVien().getSvSelected().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
            var TongTC = DiemSV_HocKy.getRepository().getTongTC(
                    Singleton.getInstant().getDiemSinhVien().getSvSelected().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
            if (diemTK != null && TongTC != null) {
                Singleton.getInstant().getDiemSinhVien().getDiemTK().set(diemTK);
                Singleton.getInstant().getDiemSinhVien().getSoTC().set(TongTC);
            } else {
                Singleton.getInstant().getViewFactory().showLog("Chưa nhập dữ liệu cho sinh viên " + Singleton.getInstant().getDiemSinhVien().getSvSelected().get());
            }
        });
    }
}
