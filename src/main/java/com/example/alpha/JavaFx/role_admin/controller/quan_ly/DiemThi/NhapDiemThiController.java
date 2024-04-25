package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemThi;

import com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemQuaTrinh.DiemQTController;
import com.example.alpha.JavaFx.role_admin.setTable;
import com.example.alpha.JavaFx.role_admin.model.*;
import com.example.alpha.JavaFx.role_admin.model.Diem.Diem;
import com.example.alpha.JavaFx.role_admin.model.Diem.LanThi;
import com.example.alpha.JavaFx.role_admin.model.Diem.PhongThi;
import com.example.alpha.Spring_boot.subject.DiemThiEntity;
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
import org.springframework.stereotype.Controller;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class NhapDiemThiController implements Initializable, setTable {
    @FXML
    private TableColumn<Object, Float> Column_Diem;
    @FXML
    private TableColumn<DiemThiEntity, String> Column_LanThi;
    @FXML
    private TableColumn<?, ?> Column_MaMonHoc;
    @FXML
    private TableColumn<?, ?> Column_MaSV;
    @FXML
    private TableView<DiemThiEntity> TableView_Diem;
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
    private Button button_import;
    @FXML
    private ChoiceBox<String> ChoiceBox_LanThi;
    @FXML
    private ComboBox<String> CombocBox_PhongThi;

    private ObservableList<DiemThiEntity> data = FXCollections.observableArrayList();

    private FilteredList<DiemThiEntity> filteredList;

    public void setCellColumn() {
        Column_MaSV.setCellValueFactory(new PropertyValueFactory<>("MaSinhVien"));
        Column_LanThi.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getLanThi()).asString());
        Column_Diem.setCellValueFactory(param -> new SimpleObjectProperty<>(((DiemThiEntity)param.getValue()).getDiem()));
        Column_Diem.setCellFactory(new DiemQTController.NullHandlingCellFactory());
        Column_MaMonHoc.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
    }
    @Override
    public void setTableView() {
        setCellColumn();
        List<DiemThiEntity> diemEntities = Diem.getRepository().findAll();
        data = FXCollections.observableArrayList(diemEntities);
        filteredList = new FilteredList<>(data, b -> true);
        TableView_Diem.setItems(filteredList);
    }

    @Override
    public void addListenerTableView() {
        TableView_Diem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 1) {
                if (TableView_Diem.getSelectionModel().getSelectedIndex() > -1) {
                    int index = TableView_Diem.getSelectionModel().getSelectedIndex();
                    DiemThiEntity kq = filteredList.get(index);

                    Label_MaSV.setText(kq.getMaSinhVien());
                    Label_MaMH.setText(kq.getMaMonHoc());
                    TextField_Diem.setText(String.valueOf(kq.getDiem()));
                }
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
                    return String.valueOf(diem.getLanThi()).equals(Singleton.getInstant().getNhapDiemThi().getLanThi().get())
                            && diem.getMaMonHoc().equals(Singleton.getInstant().getNhapDiemThi().getMonHocSelected().get())
                            && diem.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get())
                            && diem.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get())
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
            Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().set(CombocBox_PhongThi.getValue());

            load(Singleton.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    newValue,
                    String.valueOf(Singleton.getInstant().getNhapDiemThi().getLanThi().get()),
                    Singleton.getInstant().getViewFactory().getNamHoc().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get());
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

        Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().bindBidirectional(CombocBox_PhongThi.valueProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox_LanThi.setItems(FXCollections.observableArrayList(LanThi.getRepository().getLanThi()));
        ChoiceBox_LanThi.valueProperty().addListener((observable, oldValue, newValue) -> {
            Singleton.getInstant().getNhapDiemThi().getLanThi().set(ChoiceBox_LanThi.getValue());

            load(Singleton.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    newValue,
                    Singleton.getInstant().getViewFactory().getNamHoc().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get());
        });
        Singleton.getInstant().getNhapDiemThi().getLanThi().bindBidirectional(ChoiceBox_LanThi.valueProperty());

        Singleton.getInstant().getNhapDiemThi().getMonHocSelected().addListener((observable, oldValue, newValue) -> {
            load(newValue, Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get(),
                    Singleton.getInstant().getViewFactory().getHocky().get());
        });

        //Tải dữ liệu cho bảng
        setTableView();
        //Thêm Listener khi nhấn vào một dòng trong bảng
        addListenerTableView();
        //Listener của ChoiceBox mã phòng thi
        addListenerComboBox();

        button_update.setOnAction(event -> {
            DiemThiEntity kq = new DiemThiEntity(
                    Label_MaSV.getText(),
                    Label_MaMH.getText(),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get(),
                    Integer.parseInt(Singleton.getInstant().getNhapDiemThi().getLanThi().get()),
                    Float.parseFloat(TextField_Diem.getText()),
                    Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get()
            );
            Diem.getRepository().updateDiem(
                    Label_MaSV.getText(), Label_MaMH.getText(),
                    TextField_Diem.getText(),
                    Integer.parseInt(Singleton.getInstant().getNhapDiemThi().getLanThi().get()),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get()
            );

            data.removeIf(kqSinhVienMonhocEntity ->
                    kqSinhVienMonhocEntity.getMaSinhVien().equals(Label_MaSV.getText()) &&
                    kqSinhVienMonhocEntity.getLanThi()== kq.getLanThi() &&
                    kqSinhVienMonhocEntity.getMaHocKy().equals(Singleton.getInstant().getViewFactory().getHocky().get()) &&
                    kqSinhVienMonhocEntity.getMaNamHoc().equals(Singleton.getInstant().getViewFactory().getNamHoc().get()));
            data.add(kq);

            defaultLoad();
        });


        //Khi Năm thay đổi
        Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            load(Singleton.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                    newValue,
                    Singleton.getInstant().getViewFactory().getHocky().get());
        });

        //Khi học kỳ thay đổi
        Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            load(Singleton.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                    Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                    Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get(),
                    newValue);
        });

        button_import.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            // Lọc chỉ cho phép chọn file Excel
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);

            // Hiển thị cửa sổ chọn file
            File selectedFile = fileChooser.showOpenDialog(button_import.getScene().getWindow()).getAbsoluteFile();

            // Gọi hàm để đọc file Excel ở đây
            readExcelFile(selectedFile);

            setTableView();
        });
    }

    private void readExcelFile(File selectedFile) {
        try {
            Workbook workbook = WorkbookFactory.create(selectedFile);
            workbook.getNumberOfSheets();

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);

                // Duyệt qua từng hàng của sheet và in ra các giá trị
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Diem.getRepository().updateDiem(
                            String.valueOf((int) sheet.getRow(rowIndex).getCell(5).getNumericCellValue()),
                            sheet.getRow(rowIndex).getCell(3).getStringCellValue(),
                            String.valueOf(sheet.getRow(rowIndex).getCell(6).getNumericCellValue()),
                            (int) sheet.getRow(1).getCell(rowIndex).getNumericCellValue(),
                            String.valueOf((int) sheet.getRow(rowIndex).getCell(2).getNumericCellValue()),
                            String.valueOf((int) sheet.getRow(rowIndex).getCell(4).getNumericCellValue()));
                }
                workbook.close();
                Singleton.getInstant().getViewFactory().getStatus().set(Status.OK);
            }
        } catch (IOException e) {
            Singleton.getInstant().getViewFactory().getStatus().set(Status.ERROR);
            throw new RuntimeException(e);
        }
    }

    private void defaultLoad() {
        load(Singleton.getInstant().getNhapDiemThi().getMonHocSelected().get(),
                Singleton.getInstant().getNhapDiemThi().getPhongThiProperty().get(),
                Singleton.getInstant().getNhapDiemThi().getLanThi().get(),
                Singleton.getInstant().getViewFactory().getNamHoc().get(),
                Singleton.getInstant().getViewFactory().getHocky().get());
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