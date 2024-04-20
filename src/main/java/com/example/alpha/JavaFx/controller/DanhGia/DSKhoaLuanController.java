package com.example.alpha.JavaFx.controller.DanhGia;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.DiemSV_CaNam;
import com.example.alpha.JavaFx.model.Diem.PhongThi;
import com.example.alpha.JavaFx.model.Lop;
import com.example.alpha.JavaFx.model.Singleton;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DSKhoaLuanController implements Initializable, setTable {

    @FXML
    private TableView<KqSinhVienCanamEntity> Table_DSKhoaLuan;

    @FXML
    private TableColumn<KqSinhVienCanamEntity, Float> Column_DiemTK;

    @FXML
    private TableColumn<KqSinhVienCanamEntity, String> Column_MaSV;

    @FXML
    private TableColumn<KqSinhVienCanamEntity, String> Column_TenSV;

    @FXML
    private ComboBox<String> ComboBox_LopNamCuoi;

    private FilteredList<KqSinhVienCanamEntity> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();

        ComboBox_LopNamCuoi.setEditable(true);
        ComboBox_LopNamCuoi.setItems(FXCollections.observableArrayList(Lop.getRepository().getNamCuoi(LocalDate.now().getYear())));
        ComboBox_LopNamCuoi.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    kq.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()));
            Table_DSKhoaLuan.setItems(filteredList);
        });

        ComboBox_LopNamCuoi.getEditor().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            var suggestion = FXCollections.observableArrayList(PhongThi.getRepository().getMa());

            ObservableList<String> S = FXCollections.observableArrayList();
            for (String s : suggestion) {
                if (!s.equals(ComboBox_LopNamCuoi.getEditor().getText())) {
                    if (s.contains(ComboBox_LopNamCuoi.getEditor().getText())) {
                        S.add(s);
                    }
                }
            }
            if(!event.getCode().equals(KeyCode.ENTER)) {
                ComboBox_LopNamCuoi.setItems(S);
                ComboBox_LopNamCuoi.show();
            }
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(DiemSV_CaNam.getRepository().findAll()));
        System.out.println(filteredList);
        Table_DSKhoaLuan.setItems(filteredList);
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Column_TenSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
        Column_DiemTK.setCellValueFactory(param -> {
            float DiemTK = 0;
            List<Float> diems = DiemSV_CaNam.getRepository().getAllDiemNamHoc(param.getValue().getMaSinhVien());
            for(Float diem : diems){
                DiemTK += diem;
            }
            DiemTK = DiemTK/diems.size();
            return new ReadOnlyObjectWrapper<>(DiemTK);
        });
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
