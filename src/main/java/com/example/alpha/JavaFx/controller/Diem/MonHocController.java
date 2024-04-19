package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.model.HocKy;
import com.example.alpha.JavaFx.model.MonHoc.ButtonCellMH;
import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class MonHocController implements Initializable, setTable {

    @FXML
    private TableColumn<DiemEntity, Button> ColumnX;

    @FXML
    private TableColumn<DiemEntity, String> Column_MaMH;

    @FXML
    private TableColumn<DiemEntity, String> Column_TenMH;

    @FXML
    private TableView<DiemEntity> TableView_MH;

    private FilteredList<DiemEntity> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        Model.getInstant().getNhapDiemThi().getPhongThiProperty().addListener((observable, oldValue, newValue) -> {
            load(newValue,
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    Model.getInstant().getViewFactory().getHocky().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get());
        });

        Model.getInstant().getNhapDiemThi().getLanThi().addListener((observable, oldValue, newValue) -> {
            load(Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    newValue,
                    Model.getInstant().getViewFactory().getHocky().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get());
        });

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            load(Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    newValue,
                    Model.getInstant().getViewFactory().getNamHoc().get());
        });

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            load(Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    Model.getInstant().getViewFactory().getHocky().get(),
                    newValue);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(Diem.getRepository().findAll()), b -> true);
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
            for(String phongthi : Diem.getRepository().getPhongThiByMaMH(kq.getMaMonHoc())) {
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
