package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.ButtonCell;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.SinhVien;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Date;
import java.util.*;

@Controller
public class QLSinhVienController implements Initializable, setTable {
    @FXML
    private AnchorPane AnchorPane_QLSinhVien;

    @FXML
    private Button Button_Add;

    @FXML
    private Button Button_Delete;

    @FXML
    private Button Button_Update;

    @FXML
    private CheckBox CheckBox_Nam;

    @FXML
    private CheckBox CheckBox_Nu;

    @FXML
    private TableColumn<?, ?> Column_DiaChi;

    @FXML
    private TableColumn<?, ?> Column_Email;

    @FXML
    private TableColumn<?, ?> Column_GioiTinh;

    @FXML
    private TableColumn<?, ?> Column_HoTen;

    @FXML
    private TableColumn<?, ?> Column_MaSV;

    @FXML
    private TableColumn<?, ?> Column_NgaySinh;

    @FXML
    private TableColumn<SinhVienEntity, Button> ColumnX;

    @FXML
    private DatePicker DataPicker_NgaySinh;

    @FXML
    private TableView<SinhVienEntity> TableView_SinhVien;

    @FXML
    private TextArea TextArea_DiaChi;


    @FXML
    private TextField TextField_Email;

    @FXML
    private TextField TextField_HoTen;

    @FXML
    private TextField TextField_MaSV;

    @FXML
    private TextField TextField_Search;

    private SinhVienEntity sinhVien;
    private ObservableList<SinhVienEntity> data = FXCollections.observableArrayList();
    private List<SinhVienEntity> sv;
    private final SimpleBooleanProperty isFemale = new SimpleBooleanProperty();

    private SortedList<SinhVienEntity> sortedList;

    @Override
    public void setTableView() {
        setCellColumn();
        sv = SinhVien.getRepository().findAll();
        TableView_SinhVien.setItems(FXCollections.observableArrayList(sv));
    }

    @Override
    public void addListenerTableView(){
        data = FXCollections.observableArrayList(sv);
        Model.getInstant().getViewQuanLy().getSvSelected().addListener((observable, oldValue, newValue) -> {
            data.stream().filter(sinhVienEntity ->
                    sinhVienEntity.getMaSinhVien().equals(Model.getInstant().getViewQuanLy().getSvSelected().get())
            ).findFirst().ifPresent(sinhVienEntity -> {
                TextField_HoTen.setText(sinhVienEntity.getHoTen());
                TextField_Email.setText(sinhVienEntity.getEmail());
                TextArea_DiaChi.setText(sinhVienEntity.getDiaChi());

                isFemale.bind(CheckBox_Nu.selectedProperty());

                //Hien thi gioi tinh
                if (sinhVienEntity.getGioiTinh()) {
                    CheckBox_Nu.selectedProperty().set(true);
                    CheckBox_Nam.selectedProperty().set(false);
                } else {
                    CheckBox_Nam.selectedProperty().set(true);
                    CheckBox_Nu.selectedProperty().set(false);
                }
                TextField_MaSV.setText(sinhVienEntity.getMaSinhVien());
                DataPicker_NgaySinh.setValue(sinhVienEntity.getNgaySinh().toLocalDate());
            });
        });
        addListenerSearch();
    }

    @Override
    public void setCellColumn() {
        Column_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        Column_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Column_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        Column_HoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        ColumnX.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(new ButtonCell().getButton()));

        System.out.println(ColumnX.getColumns());
    }

    @Override
    public void addListenerSearch(){
        FilteredList<SinhVienEntity> filteredList = new FilteredList<>(data, b->true);
        TextField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(sinhVienEntity -> {
                if(newValue.isBlank() || newValue.isEmpty()){
                    return true;
                }
                String id = newValue.toLowerCase();
                return sinhVienEntity.getMaSinhVien().toLowerCase().contains(id) ||
                        sinhVienEntity.getHoTen().toLowerCase().contains(id) ||
                        sinhVienEntity.getDiaChi().toLowerCase().contains(id) ||
                        sinhVienEntity.getNgaySinh().toString().toLowerCase().contains(id);
            });
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            sortedList.comparatorProperty().bind(TableView_SinhVien.comparatorProperty());
            TableView_SinhVien.setItems(sortedList);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        addListenerTableView();

//        CheckBox_Nu.setDisable(true);
//        CheckBox_Nam.setDisable(true);

        Button_Add.setOnAction(event -> {
            SinhVienEntity sinhVienEntity = new SinhVienEntity(TextField_MaSV.getText(),TextField_HoTen.getText(),isFemale.get(), Date.valueOf(DataPicker_NgaySinh.getValue()),TextArea_DiaChi.getText(), TextField_Email.getText());
            SinhVien.getRepository().save(sinhVienEntity);
            data.add(sinhVienEntity);
            TableView_SinhVien.setItems(data);
        });

        Button_Delete.setOnAction(event -> {
            SinhVien.getRepository().deleteById(TextField_MaSV.getText());
            data.removeIf(entity -> entity.getMaSinhVien().equals(TextField_MaSV.getText()));
            TableView_SinhVien.setItems(data);
        });
    }
}
