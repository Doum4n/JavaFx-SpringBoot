package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemThi;

import com.example.alpha.JavaFx.role_admin.model.MonHoc.ButtonCellMH;
import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.Diem.Diem;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.MonHoc.MonHoc;
import com.example.alpha.Spring_boot.subject.DiemThiEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class MonHocController implements Initializable, setTable {

    @FXML
    private TableColumn<DiemThiEntity, Button> ColumnX;

    @FXML
    private TableColumn<DiemThiEntity, String> Column_MaMH;

    @FXML
    private TableColumn<DiemThiEntity, String> Column_TenMH;

    @FXML
    private TableView<DiemThiEntity> TableView_MH;

    private FilteredList<DiemThiEntity> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().addListener((observable, oldValue, newValue) -> {
            load(newValue,
                    Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
        });

        Singleton.getInstant().getNhapDiemThi().getLanThi().addListener((observable, oldValue, newValue) -> {
            load(Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    newValue,
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
        });

        Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            load(Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                    newValue,
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
        });

        Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            load(Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    newValue);
        });

    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<String> MonHoc = new ArrayList<>(Collections.emptyList());
        filteredList = new FilteredList<>(FXCollections.observableArrayList(Diem.getRepository().findAll().stream().filter(diemEntity -> {
            if (!MonHoc.contains(diemEntity.getMaMonHoc())) {
                MonHoc.add(diemEntity.getMaMonHoc());
                return true;
            }
            return false;
        }).toList()), b -> true);
        TableView_MH.setItems(filteredList);
    }

    @Override
    public void setCellColumn() {
        ColumnX.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(new ButtonCellMH().getButton()));
        Column_MaMH.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaMonHoc()));
        Column_TenMH.setCellValueFactory(param -> new SimpleObjectProperty<>(MonHoc.getRepository().getTenMH(param.getValue().getMaMonHoc())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }

    private void load(String PhongThi, String LanThi, String MaHocKy, String MaNamHoc){
        filteredList.setPredicate(kq -> {
            AtomicBoolean b = new AtomicBoolean(false);
            for(String phongthi : Diem.getRepository().getPhongThiByMaMH(kq.getMaMonHoc(), kq.getMaHocKy(), kq.getMaNamHoc())) {
                if(phongthi.equals(PhongThi) &&
                        String.valueOf(kq.getLanThi()).equals(LanThi) &&
                        kq.getMaHocKy().equals(MaHocKy) &&
                        kq.getMaNamHoc().equals(MaNamHoc))
                {
                    b.set(true);
                    break;
                }
            }
            return b.get();
        });
        TableView_MH.setItems(filteredList);
    }
}
