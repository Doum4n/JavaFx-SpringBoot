package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.model.Diem;
import com.example.alpha.JavaFx.model.Model;
import com.example.alpha.JavaFx.model.PhongThi;
import com.example.alpha.Spring_boot.subject.DiemEntity;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Date;
import java.util.*;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class DiemController implements Initializable, setTable {
    @FXML
    private TableColumn<?, ?> Column_Diem;

    @FXML
    private TableColumn<?, ?> Column_LanThi;

    @FXML
    private TableColumn<?, ?> Column_MaMonHoc;

    @FXML
    private TableColumn<?, ?> Column_MaSV;

    @FXML
    private TableView<DiemEntity> TableView_Diem;

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
    private ChoiceBox<String> ChoiceBox_PhongThi;
    private ObservableList<DiemEntity> data = FXCollections.observableArrayList();
    private SortedList<DiemEntity> sortedList;

    private List<DiemEntity> diemEntities;
    FilteredList<DiemEntity> filteredList;
    private final StringProperty PhongThiProperty = new SimpleStringProperty();

    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_Diem.setCellValueFactory(new PropertyValueFactory<>("Diem"));
        Column_LanThi.setCellValueFactory(new PropertyValueFactory<>("LanThi"));
        Column_MaMonHoc.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
    }

    @Override
    public void setTableView() {
        setCellColumn();
        diemEntities = Diem.getRepository().findAll();
        TableView_Diem.setItems(FXCollections.observableArrayList(diemEntities));
    }

    @Override
    public void addListenerTableView() {
        data = FXCollections.observableArrayList(diemEntities);
        TableView_Diem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                int index = TableView_Diem.getSelectionModel().getSelectedIndex();
                DiemEntity diemEntity = sortedList.get(index);

                TextField_MaSV.setText(diemEntity.getMaSinhVien());
                TextField_MaMonHoc.setText(diemEntity.getMaMonHoc());
                TextField_LanThi.setText(String.valueOf(diemEntity.getLanThi()));
                TextField_Diem.setText(String.valueOf(diemEntity.getDiem()));
            }
        });
        addListenerSearch();
    }


    public void addListenerSearch() {
//        System.out.println(data);
        FilteredList<DiemEntity> filteredList = new FilteredList<>(data, b->true);
        textField_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> {
                if(newValue.isBlank() || newValue.isEmpty()){
                    return true;
                }
                String id = newValue.toLowerCase();
                return diemEntity.getMaSinhVien().toLowerCase().contains(id);
            });
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            sortedList.comparatorProperty().bind(TableView_Diem.comparatorProperty());
            TableView_Diem.setItems(sortedList);
        });
    }

    private void addListenerChoiceBox(){
        filteredList = new FilteredList<>(data, b->true);
        ChoiceBox_PhongThi.setItems(FXCollections.observableArrayList(PhongThi.getRepository().getMa()));
        ChoiceBox_PhongThi.setValue(PhongThi.getRepository().getMa().get(0));
        ChoiceBox_PhongThi.valueProperty().addListener((observable, oldValue, newValue) -> {
            PhongThiProperty.set(ChoiceBox_PhongThi.getValue());

//            filteredList = new FilteredList<>(data, b->true);

            filteredList.setPredicate(diemEntity -> diemEntity.getPhongThi().equals(newValue) &&
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
        setTableView();
        addListenerTableView();
        addListenerChoiceBox();
        button_update.setOnAction(event -> {
            DiemEntity diemEntity = new DiemEntity(
                    TextField_MaSV.getText(),
                    TextField_MaMonHoc.getText(),
                    Model.getInstant().getViewFactory().getHocky().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    Double.parseDouble(TextField_Diem.getText()),
                    Integer.parseInt(TextField_LanThi.getText()),
                    PhongThiProperty.get()
            );
            Diem.getRepository().save(diemEntity);
            data.add(diemEntity);

//            filteredList = new FilteredList<>(data, b->true);

            filteredList.setPredicate(diemEntity1 -> diemEntity1.getPhongThi().equals(diemEntity.getPhongThi()) &&
                    diemEntity1.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()) &&
                    diemEntity1.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
            System.out.println(filteredList);
        });

        button_delete.setOnAction(event -> {
            Diem.getRepository().deleteByMaSinhVien(TextField_MaSV.getText());
            data.removeIf(entity -> entity.getMaSinhVien().equals(TextField_MaSV.getText()));

            filteredList.removeIf(entity -> entity.getMaSinhVien().equals(TextField_MaSV.getText()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
        });



        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "2025")){
                button_update.setVisible(false);
                button_delete.setVisible(false);
            }else {
                button_update.setVisible(true);
                button_delete.setVisible(true);
            }

            filteredList.setPredicate(diemEntity -> diemEntity.getMaNamHoc().equals(newValue) &&
                    diemEntity.getPhongThi().equals(PhongThiProperty.get()) &&
                    diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
        });

        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "1")){
                button_update.setVisible(false);
                button_delete.setVisible(false);
            }else {
                button_update.setVisible(true);
                button_delete.setVisible(true);
            }
            filteredList.setPredicate(diemEntity -> diemEntity.getMaHocKy().equals(newValue) &&
                    diemEntity.getPhongThi().equals(PhongThiProperty.get()) &&
                    diemEntity.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get()));
            sortedList = new SortedList<>(FXCollections.observableArrayList(filteredList));
            TableView_Diem.setItems(sortedList);
        });
    }
}
