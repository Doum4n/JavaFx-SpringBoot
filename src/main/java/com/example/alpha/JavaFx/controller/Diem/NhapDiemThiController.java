package com.example.alpha.JavaFx.controller.Diem;

import com.example.alpha.JavaFx.controller.setTable;
import com.example.alpha.JavaFx.model.*;
import com.example.alpha.JavaFx.model.Diem.Diem;
import com.example.alpha.JavaFx.model.Diem.LanThi;
import com.example.alpha.JavaFx.model.Diem.PhongThi;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class NhapDiemThiController implements Initializable, setTable {
    @FXML
    private TableColumn<DiemEntity, Double> Column_Diem;
    @FXML
    private TableColumn<DiemEntity, String> Column_LanThi;
    @FXML
    private TableColumn<?, ?> Column_MaMonHoc;
    @FXML
    private TableColumn<?, ?> Column_MaSV;
    @FXML
    private TableView<DiemEntity> TableView_Diem;
    @FXML
    private TextField TextField_Diem;
    @FXML
    private Button button_delete;
    @FXML
    private TextField textField_search;
    @FXML
    private Label Label_MaMH;
    @FXML
    private Label Label_MaSV;
    @FXML
    private Button button_update;
    @FXML
    private Button buyyon_import;
    @FXML
    private ChoiceBox<String> ChoiceBox_LanThi;
    @FXML
    private ComboBox<String> CombocBox_PhongThi;

    private ObservableList<DiemEntity> data = FXCollections.observableArrayList();

    private FilteredList<DiemEntity> filteredList;

    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_LanThi.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getLanThi()).asString());
        Column_Diem.setCellValueFactory(param -> new SimpleObjectProperty<>(Double.valueOf(param.getValue().getDiem())));
        Column_MaMonHoc.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
    }
    @Override
    public void setTableView() {
        setCellColumn();
        List<DiemEntity> diemEntities = Diem.getRepository().findAll();
        data = FXCollections.observableArrayList(diemEntities);
        filteredList = new FilteredList<>(data, b -> true);
        TableView_Diem.setItems(filteredList);
    }

    @Override
    public void addListenerTableView() {
        TableView_Diem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                int index = TableView_Diem.getSelectionModel().getSelectedIndex();
                DiemEntity kq = filteredList.get(index);

                Label_MaSV.setText(kq.getMaSinhVien());
                Label_MaMH.setText(kq.getMaMonHoc());
                TextField_Diem.setText(String.valueOf(kq.getDiem()));
            }
        });
        addListenerSearch();
    }

    public void addListenerSearch() {
        filteredList = new FilteredList<>(data, b->true);
        textField_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isBlank()) {
                filteredList.setPredicate(diem -> {
                    String id = newValue.toLowerCase();
                    return String.valueOf(diem.getLanThi()).equals(Model.getInstant().getNhapDiemThi().getLanThi().get())
                            && diem.getMaMonHoc().equals(Model.getInstant().getNhapDiemThi().getMonHocSelected().get())
                            && diem.getMaNamHoc().equals(Model.getInstant().getViewFactory().getNamHoc().get())
                            && diem.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get())
                            && diem.getMaSinhVien().equals(id);
                });
                TableView_Diem.setItems(filteredList);
            }else {
                defaultLoad();
            }
        });
    }

    private void addListenerComboBox(){
        CombocBox_PhongThi.setEditable(true);
        CombocBox_PhongThi.setItems(FXCollections.observableArrayList(PhongThi.getRepository().getMa()));
        //Lọc dữ liệu
        CombocBox_PhongThi.valueProperty().addListener((observable, oldValue, newValue) -> {
            Model.getInstant().getNhapDiemThi().getPhongThiProperty().set(CombocBox_PhongThi.getValue());

            load(Model.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    newValue,
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    Model.getInstant().getViewFactory().getHocky().get());
        });
        //Hiện đề xuất
        CombocBox_PhongThi.getEditor().addEventHandler(KeyEvent.KEY_RELEASED,event -> {
            var suggestion = FXCollections.observableArrayList(PhongThi.getRepository().getMa());

            ObservableList<String> S = FXCollections.observableArrayList();
            for (String s : suggestion) {
                if (!s.equals(CombocBox_PhongThi.getEditor().getText())) {
                    if (s.contains(CombocBox_PhongThi.getEditor().getText())) {
                        S.add(s);
                    }
                }
            }
            if(!event.getCode().equals(KeyCode.ENTER)) {
                CombocBox_PhongThi.setItems(S);
                CombocBox_PhongThi.show();
            }
        });

        Model.getInstant().getNhapDiemThi().getPhongThiProperty().bindBidirectional(CombocBox_PhongThi.valueProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox_LanThi.setItems(FXCollections.observableArrayList(LanThi.getRepository().getLanThi()));
        ChoiceBox_LanThi.valueProperty().addListener((observable, oldValue, newValue) -> {
            Model.getInstant().getNhapDiemThi().getLanThi().set(ChoiceBox_LanThi.getValue());

            load(Model.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    newValue,
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    Model.getInstant().getViewFactory().getHocky().get());
        });
        Model.getInstant().getNhapDiemThi().getLanThi().bindBidirectional(ChoiceBox_LanThi.valueProperty());

        Model.getInstant().getNhapDiemThi().getMonHocSelected().addListener((observable, oldValue, newValue) -> {
            load(newValue, Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    Model.getInstant().getViewFactory().getHocky().get());
        });

        //Tải dữ liệu cho bảng
        setTableView();
        //Thêm Listener khi nhấn vào một dòng trong bảng
        addListenerTableView();
        //Listener của ChoiceBox mã phòng thi
        addListenerComboBox();

        button_update.setOnAction(event -> {
            DiemEntity kq = new DiemEntity(
                    Label_MaSV.getText(),
                    Label_MaMH.getText(),
                    Model.getInstant().getViewFactory().getHocky().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    Integer.parseInt(Model.getInstant().getNhapDiemThi().getLanThi().get()),
                    TextField_Diem.getText(),
                    Model.getInstant().getNhapDiemThi().getPhongThiProperty().get()
            );
            Diem.getRepository().updateDiem(Label_MaSV.getText(), Label_MaMH.getText(), TextField_Diem.getText(), Integer.parseInt(Model.getInstant().getNhapDiemThi().getLanThi().get()));

            data.removeIf(kqSinhVienMonhocEntity -> kqSinhVienMonhocEntity.getMaSinhVien().equals(Label_MaSV.getText()) && kqSinhVienMonhocEntity.getLanThi()== kq.getLanThi());
            data.add(kq);

            defaultLoad();
        });

        button_delete.setOnAction(event -> {
            Diem.getRepository().deleteByMaSinhVien(Label_MaSV.getText());
            data.removeIf(entity -> entity.getMaSinhVien().equals(Label_MaMH.getText()));
            filteredList.removeIf(entity -> entity.getMaSinhVien().equals(Label_MaSV.getText()));
            defaultLoad();
        });

        //Khi Năm thay đổi
        Model.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(diemEntity -> diemEntity.getMaNamHoc().equals(newValue) &&
                    Diem.getRepository().getPhongThi(diemEntity.getMaSinhVien(),diemEntity.getMaMonHoc()).equals(Model.getInstant().getNhapDiemThi().getPhongThiProperty().get()) &&
                    diemEntity.getMaHocKy().equals(Model.getInstant().getViewFactory().getHocky().get()));
            load(Model.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    newValue,
                    Model.getInstant().getViewFactory().getHocky().get());
        });

        //Khi học kỳ thay đổi
        Model.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            load(Model.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Model.getInstant().getNhapDiemThi().getLanThi().get(),
                    Model.getInstant().getViewFactory().getNamHoc().get(),
                    newValue);
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

    private void defaultLoad() {
        load(Model.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                Model.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                Model.getInstant().getNhapDiemThi().getLanThi().get(),
                Model.getInstant().getViewFactory().getNamHoc().get(),
                Model.getInstant().getViewFactory().getHocky().get());
        TableView_Diem.setItems(filteredList);
    }

    private void load(String MonHoc, String PhongThi, String LanThi, String NamHoc, String HocKy){
        filteredList.setPredicate(diem -> String.valueOf(diem.getLanThi()).equals(LanThi)
                && String.valueOf(diem.getPhongThi()).equals(PhongThi)
                && diem.getMaMonHoc().equals(MonHoc)
                && diem.getMaNamHoc().equals(NamHoc)
                && diem.getMaHocKy().equals(HocKy));
        TableView_Diem.setItems(filteredList);
    }
}