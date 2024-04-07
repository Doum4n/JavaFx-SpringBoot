package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Diem;
import com.example.alpha.JavaFx.model.KqMonHoc_SV;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhongThi;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.*;

@Controller
public class DiemController implements Initializable, setTable {

    @FXML
    private TableColumn<KqSinhVienMonhocEntity, Double> Column_Diem;
    @FXML
    private TableColumn<?, ?> Column_LanThi;
    @FXML
    private TableColumn<?, ?> Column_MaMonHoc;
    @FXML
    private TableColumn<?, ?> Column_MaSV;
    @FXML
    private TableView<KqSinhVienMonhocEntity> TableView_Diem;
    @FXML
    private TextField TextField_Diem;
    @FXML
    private TextField TextField_LanThi;
    @FXML
    private TextField TextField_MaMonHoc;
    @FXML
    private TextField TextField_MaSV;
    @FXML
    private Button button_delete;
    @FXML
    private TextField textField_search;
    @FXML
    private Button button_update;
    @FXML
    private Button buyyon_import;
    @FXML
    private ChoiceBox<String> ChoiceBox_PhongThi;

    private ObservableList<KqSinhVienMonhocEntity> data = FXCollections.observableArrayList();
    private SortedList<KqSinhVienMonhocEntity> sortedList;
    private FilteredList<KqSinhVienMonhocEntity> filteredList;
    private StringProperty PhongThiProperty = null;

    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_Diem.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDiemThi()));
        Column_LanThi.setCellValueFactory(new PropertyValueFactory<>("LanThi"));
        Column_MaMonHoc.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
    }

    @Override
    public void setTableView() {
        setCellColumn();
        List<KqSinhVienMonhocEntity> diemEntities = KqMonHoc_SV.getRepository().findAll();

        data = FXCollections.observableArrayList(diemEntities);
        PhongThiProperty = new SimpleStringProperty(PhongThi.getRepository().getMa().get(0));

        filteredList = new FilteredList<>(data, b -> true);

        filteredList.forEach(kqSinhVienMonhocEntity -> {
            System.out.println(kqSinhVienMonhocEntity.getMaSinhVien()+" "+kqSinhVienMonhocEntity.getMaMonHoc());
            System.out.println(Diem.getRepository().getPhongThi(kqSinhVienMonhocEntity.getMaSinhVien(),kqSinhVienMonhocEntity.getMaMonHoc()));
        });
        filteredList.setPredicate(diemEntity -> Diem.getRepository().getPhongThi(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc()).equals(PhongThiProperty.get()) &&
                diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()));

        sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
        TableView_Diem.setItems(sortedList);
        System.out.println(Model.getInstant().getViewFactory().getHocky());
    }

    @Override
    public void addListenerTableView() {
        TableView_Diem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                int index = TableView_Diem.getSelectionModel().getSelectedIndex();
                KqSinhVienMonhocEntity kq = sortedList.get(index);

                TextField_MaSV.setText(kq.getMaSinhVien());
                TextField_MaMonHoc.setText(kq.getMaMonHoc());
                TextField_LanThi.setText(String.valueOf(kq.getLanThi()));
                TextField_Diem.setText(String.valueOf(kq.getDiemThi()));
            }
        });
        addListenerSearch();
    }


    public void addListenerSearch() {
        filteredList = new FilteredList<>(data, b->true);
        textField_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                filteredList.setPredicate(diemEntity -> {
                    String id = newValue.toLowerCase();
                    return Diem.getRepository().getPhongThi(diemEntity.getMaSinhVien(), diemEntity.getMaMonHoc()).equals(PhongThiProperty.get())
                            && diemEntity.getMaSinhVien().toLowerCase().contains(id)
                            && diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get())
                            && diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get());
                });
                sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
                sortedList.comparatorProperty().bind(TableView_Diem.comparatorProperty());
                TableView_Diem.setItems(sortedList);
            }else {
                load();
                TableView_Diem.setItems(filteredList);
            }
        });
    }

    private void addListenerChoiceBox(){
        filteredList = new FilteredList<>(data, b->true);
        ChoiceBox_PhongThi.setItems(FXCollections.observableArrayList(PhongThi.getRepository().getMa()));
        ChoiceBox_PhongThi.setValue(PhongThi.getRepository().getMa().get(0));
        ChoiceBox_PhongThi.valueProperty().addListener((observable, oldValue, newValue) -> {
            PhongThiProperty.set(ChoiceBox_PhongThi.getValue());

            filteredList.setPredicate(diemEntity -> Diem.getRepository().getPhongThi(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc()).equals(newValue) &&
                    diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()) &&
                    diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            sortedList.comparatorProperty().bind(TableView_Diem.comparatorProperty());

            TableView_Diem.setItems(sortedList);
            System.out.println(newValue);
        });
        PhongThiProperty.bindBidirectional(ChoiceBox_PhongThi.valueProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Tải dữ liệu cho bảng
        setTableView();
        //Thêm Listener khi nhấn vào một dòng trong bảng
        addListenerTableView();
        //Listener của ChoiceBox mã phòng thi
        addListenerChoiceBox();

        button_update.setOnAction(event -> {
            KqSinhVienMonhocEntity kq = new KqSinhVienMonhocEntity(
                    TextField_MaSV.getText(),
                    TextField_MaMonHoc.getText(),
                    Model.getInstant().getViewFactory().getHocky().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    Double.parseDouble(TextField_Diem.getText()),
                    Integer.parseInt(TextField_LanThi.getText())
            );
            KqMonHoc_SV.getRepository().save(kq);

            data.add(kq);

            load();
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
            System.out.println(filteredList);
        });

        button_delete.setOnAction(event -> {
            Diem.getRepository().deleteByMaSinhVien(TextField_MaSV.getText());
            data.removeIf(entity -> entity.getMaSinhVien().equals(TextField_MaSV.getText()));

            filteredList.removeIf(entity -> entity.getMaSinhVien().equals(TextField_MaSV.getText()));
            load();
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
        });

        //Khi Năm thay đổi
        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> diemEntity.getMaNamHoc().equals(newValue) &&
                    Diem.getRepository().getPhongThi(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc()).equals(PhongThiProperty.get()) &&
                    diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
        });

        //Khi học kỳ thay đổi
        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> diemEntity.getMaHocKy().equals(newValue) &&
                    Diem.getRepository().getPhongThi(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc()).equals(PhongThiProperty.get()) &&
                    diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
        });


        buyyon_import.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.showOpenDialog(new PopupWindow() {
                @Override
                public void show(Window window) {
                    super.show(window);
                }
            });
        });
    }

    private void load(){
        filteredList.setPredicate(diemEntity1 -> Diem.getRepository().getPhongThi(diemEntity1.getMaSinhVien(),diemEntity1.getMaMonHoc()).equals(PhongThiProperty.get()) &&
                diemEntity1.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                diemEntity1.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
    }
}
