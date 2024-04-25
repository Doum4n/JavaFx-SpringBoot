package com.example.alpha.JavaFx.role_admin.controller.DanhGia;

import com.example.alpha.JavaFx.role_admin.model.Diem.Diem;
import com.example.alpha.JavaFx.role_admin.model.Diem.DiemRenLuyen;
import com.example.alpha.JavaFx.role_admin.model.Diem.DiemSV_HocKy;
import com.example.alpha.JavaFx.role_admin.model.Lop;
import com.example.alpha.JavaFx.role_admin.model.Singleton;
import com.example.alpha.JavaFx.role_admin.model.PhanLop;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.DKHocPhan;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.SinhVien;
import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.Spring_boot.result.student.KqSinnhVienHocKy;
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
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class DSHocBongController implements Initializable, setTable {

    @FXML
    private TableColumn<KqSinnhVienHocKy, Integer> Column_DiemRL;

    @FXML
    private TableColumn<KqSinnhVienHocKy, Float> Column_DiemTK;

    @FXML
    private TableColumn<KqSinnhVienHocKy, String> Column_MaSV;

    @FXML
    private TableColumn<KqSinnhVienHocKy, String> Column_Ten;

    @FXML
    private ComboBox<String> ComboBox_Lop;

    @FXML
    private TableView<KqSinnhVienHocKy> TableView_DS;

    private FilteredList<KqSinnhVienHocKy> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        addListenerComboBox();

        Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ComboBox_Lop.getValue()) &&
                    kq.getMaHocKy().equals(newValue) &&
                    kq.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()));
            TableView_DS.setItems(filteredList);
        });

        Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(ComboBox_Lop.getValue()) &&
                    kq.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get()) &&
                    kq.getMaNamHoc().equals(newValue));
            TableView_DS.setItems(filteredList);
        });
    }

    private void addListenerComboBox(){
        ComboBox_Lop.setEditable(true);
        ComboBox_Lop.setItems(FXCollections.observableArrayList(Lop.getRepository().getAllLop()));
        ComboBox_Lop.setValue(Lop.getRepository().getAllLop().get(0));

        ComboBox_Lop.valueProperty().addListener((observable, oldValue, newValue) -> {
            Singleton.getInstant().getDsTop10percentSVLop().getLop().set(newValue);
            filteredList.setPredicate(kq -> PhanLop.getRepository().getLop(kq.getMaSinhVien()).equals(newValue) &&
                    kq.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get()) &&
                    kq.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()));
            TableView_DS.setItems(filteredList);
        });

        ComboBox_Lop.getEditor().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            var suggestion = FXCollections.observableArrayList(Lop.getRepository().getAllLop());

            ObservableList<String> S = FXCollections.observableArrayList();
            for (String s : suggestion) {
                if (!s.equals(ComboBox_Lop.getEditor().getText())) {
                    if (s.contains(ComboBox_Lop.getEditor().getText())) {
                        S.add(s);
                    }
                }
            }
            if(!event.getCode().equals(KeyCode.ENTER)) {
                ComboBox_Lop.setItems(S);
                ComboBox_Lop.show();
            }
        });
    }

    @Override
    public void setTableView() {
        setCellColumn();
        filteredList = new FilteredList<>(FXCollections.observableArrayList(DiemSV_HocKy.getRepository().findAllSV_HocBong()));
        isThiL1GreaterThan5();
    }

    private void isThiL1GreaterThan5(){
        filteredList.setPredicate(kq -> {
            AtomicBoolean pass = new AtomicBoolean(true);
            for(String MH : DKHocPhan.getRepository().getMonhocDK(kq.getMaSinhVien())){
                Float DiemThiL1 = Diem.getRepository().getDiemThiL1(kq.getMaSinhVien(), MH, kq.getMaHocKy(), kq.getMaNamHoc());
                if(DiemThiL1!=null) {
                    System.out.println(DiemThiL1);
                    if (DiemThiL1 < 5.0) {
                        pass.set(false);
                        break;
                    }
                }
            }
            return pass.get();
        });
        TableView_DS.setItems(FXCollections.observableArrayList(filteredList));
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMaSinhVien()));
        Column_Ten.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(SinhVien.getRepository().getByHoTen(param.getValue().getMaSinhVien())));
        Column_DiemTK.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDiemTkHocKy()));
        Column_DiemRL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(DiemRenLuyen.getRepository().getDiemRenLuyen(param.getValue().getMaSinhVien(), param.getValue().getMaHocKy(), param.getValue().getMaNamHoc())));
    }

    @Override
    public void addListenerTableView() {

    }

    @Override
    public void addListenerSearch() {

    }
}
