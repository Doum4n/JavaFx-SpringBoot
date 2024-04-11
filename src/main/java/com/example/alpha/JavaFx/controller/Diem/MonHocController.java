package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.model.MonHoc.ButtonCellMH;
import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.MonHoc.MonHoc;
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
    private TableColumn<MonhocEntity, Button> ColumnX;

    @FXML
    private TableColumn<MonhocEntity, String> Column_MaMH;

    @FXML
    private TableColumn<MonhocEntity, String> Column_TenMH;

    @FXML
    private TableView<MonhocEntity> TableView_MH;

    private ObservableList<MonhocEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        Model.getInstant().getNhapDiemThi().getPhongThiProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<MonhocEntity> filteredList = new FilteredList<>(data,b -> true);

            AtomicBoolean b = new AtomicBoolean(false);
            filteredList.setPredicate(monhocEntity -> {
                Diem.getRepository().getPhongThiByMaMH(monhocEntity.getMaMonHoc()).forEach(s -> {
                    if(s.equals(newValue)){
                        b.set(true);
                    }
//                    b.set(false);
                });
                return b.get();
            });

//            data.forEach(monhocEntity -> Diem.getRepository().getPhongThiByMaMH(monhocEntity.getMaMonHoc())
//                    .forEach(s -> filteredList.setPredicate(monhocEntity1 -> s.equals(newValue))));
            TableView_MH.setItems(filteredList);
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<MonhocEntity> monhocEntities = MonHoc.getRepository().findAll();
        data = FXCollections.observableArrayList(monhocEntities);
        TableView_MH.setItems(data);
    }

    @Override
    public void setCellColumn() {
        ColumnX.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(new ButtonCellMH().getButton()));
        Column_MaMH.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMaMonHoc()));
        Column_TenMH.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTenMonHoc()));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
