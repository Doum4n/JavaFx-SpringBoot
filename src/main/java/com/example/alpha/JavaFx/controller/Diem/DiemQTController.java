package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.Diem.DiemQT;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhanLop;
import com.example.alpha.JavaFx.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.SinhVien.SinhVien;
import com.example.alpha.Spring_boot.result.student.KqSVMonHoc;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntityPK;
import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DiemQTController implements Initializable, setTable {
    @FXML
    private TableColumn<Object, Float> Column_DiemQT;

    @FXML
    private TableColumn<KqSVMonHoc, String> Column_MaSV;

    @FXML
    private TableColumn<KqSVMonHoc, String> Column_TenSV;

    @FXML
    private TableView<KqSVMonHoc> TableView_Diem;

    private ObservableList<KqSVMonHoc> data = FXCollections.observableArrayList();

    private FilteredList<KqSVMonHoc> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        addListenerTableView();
        addListenerSearch();

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(kq -> kq.MaHocKy().equals(newValue) &&
                    kq.MaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            TableView_Diem.setItems(filteredList);
        });

        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(kq -> kq.MaNamHoc().equals(newValue) &&
                    kq.MaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()));
            TableView_Diem.setItems(filteredList);
        });

        //Khi nhấn vào xem, cập nhật lớp và môn học
        Model.getInstant().getDiemQuaTrinh().getLopSelected().addListener((observable, oldValue, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(kq -> {
                System.out.println(PhanLop.getRepository().getLop(kq.MaSinhVien())+" "+ newValue);
                System.out.println(kq.MaMonHoc() +" "+ Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get());
                return kq.MaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                        kq.MaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                        Objects.equals(PhanLop.getRepository().getLop(kq.MaSinhVien()), newValue) &&
                        kq.MaMonHoc().equals(Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get());
            });
            TableView_Diem.setItems(filteredList);
        });

        Model.getInstant().getDiemQuaTrinh().getMaMHSelected().addListener((observable1, oldValue1, newValue) -> {
            filteredList = new FilteredList<>(data, b-> true);
            filteredList.setPredicate(kq -> kq.MaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                    kq.MaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                    Objects.equals(PhanLop.getRepository().getLop(kq.MaSinhVien()), Model.getInstant().getDiemQuaTrinh().getLopSelected().get()) &&
                    kq.MaMonHoc().equals(newValue));
            TableView_Diem.setItems(filteredList);
        });

        //Khi nhấn update
        Model.getInstant().getDiemQuaTrinh().getIsUpdate().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(true)){
                KqSVMonHoc diemQT = new KqSVMonHoc(
                        Model.getInstant().getDiemQuaTrinh().getMaSV().get(),
                        Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get(),
                        Model.getInstant().getViewFactory().getHocky().get(),
                        Model.getInstant().getViewFactory().getNamHoc().get(),
                        Float.valueOf(Model.getInstant().getDiemQuaTrinh().getDiem().get())
                );

                data.removeIf(kq -> kq.MaSinhVien().equals(diemQT.MaSinhVien()) && kq.MaMonHoc().equals(diemQT.MaMonHoc()));
                data.add(diemQT);

                defaultsLoad();
            }
        });
    }

    private void defaultsLoad() {
        filteredList.setPredicate(kq -> kq.MaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                kq.MaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                Objects.equals(PhanLop.getRepository().getLop(kq.MaSinhVien()), Model.getInstant().getDiemQuaTrinh().getLopSelected().get()) &&
                kq.MaMonHoc().equals(Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get()));
        TableView_Diem.setItems(filteredList);
    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<KqSVMonHoc> kqSVMonHocs = convertKqSMonHoc(KqMonHoc_SV.getRepository().findAllExceptDiemThi());
        data = FXCollections.observableArrayList(kqSVMonHocs);
        TableView_Diem.setItems(data);
    }

    private List<KqSVMonHoc> convertKqSMonHoc(List<KqSinhVienMonhocEntity> KQ) {
        List<KqSVMonHoc> kqSVMonHocs = new ArrayList<>();
        for(KqSinhVienMonhocEntity kq : KQ){
            KqSVMonHoc kqSVMonHoc = new KqSVMonHoc(kq.getMaSinhVien(),kq.getMaMonHoc(),kq.getMaHocKy(),kq.getMaNamHoc(),kq.getDiemQuaTrinh());
            kqSVMonHocs.add(kqSVMonHoc);
        }
        return kqSVMonHocs;
    }

    @Override
    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().MaSinhVien()));
        Column_TenSV.setCellValueFactory(param -> new SimpleStringProperty(SinhVien.getRepository().getByHoTen(param.getValue().MaSinhVien())));
        Column_DiemQT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(((KqSVMonHoc)param.getValue()).diemQT()));
        Column_DiemQT.setCellFactory(new NullHandlingCellFactory());
    }

    @Override
    public void addListenerTableView() {
        TableView_Diem.setSelectionModel(TableView_Diem.getSelectionModel());
        TableView_Diem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            TableView_Diem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(TableView_Diem.getSelectionModel().getSelectedIndex()  > -1) {
                    KqSVMonHoc diemQT = filteredList.get(TableView_Diem.getSelectionModel().getSelectedIndex());
                    Model.getInstant().getDiemQuaTrinh().getDiem().set(String.valueOf(diemQT.diemQT()));
                    Model.getInstant().getDiemQuaTrinh().getMaSV().set(diemQT.MaSinhVien());
                }
            });
        });
    }

    @Override
    public void addListenerSearch() {
        //Tìm kiếm gián tiếp thông qua StringProperty Search của DiemQuaTrinh
        Model.getInstant().getDiemQuaTrinh().getSearch().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                filteredList.setPredicate(diemQTEntity -> diemQTEntity.MaMonHoc().equals(Model.getInstant().getDiemQuaTrinh().getMaMHSelected().get()) &&
                        diemQTEntity.MaSinhVien().contains(newValue) &&
                        diemQTEntity.MaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                        diemQTEntity.MaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                        Objects.equals(PhanLop.getRepository().getLop(diemQTEntity.MaSinhVien()), Model.getInstant().getDiemQuaTrinh().getLopSelected().get()));
                TableView_Diem.setItems(filteredList);
            }else {
                filteredList = new FilteredList<>(data, b-> true);
                defaultsLoad();
            }
        });
    }
    public static class NullHandlingCellFactory implements Callback<TableColumn<Object, Float>, TableCell<Object, Float>> {
        @Override
        public TableCell<Object, Float> call(TableColumn<Object, Float> param) {
            TableCell<Object, Float> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.when(cell.emptyProperty())
                    .then("") // Or any other default value
                    .otherwise(Bindings.format("%.2f", cell.itemProperty())));
            return cell;
        }
    }
}
