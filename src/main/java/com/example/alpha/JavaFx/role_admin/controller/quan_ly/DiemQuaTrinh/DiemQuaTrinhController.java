package com.example.alpha.JavaFx.role_admin.controller.quan_ly.DiemQuaTrinh;

import com.example.alpha.JavaFx.role_admin.model.*;
import com.example.alpha.JavaFx.role_admin.model.GiaoVien.PhanCong;
import com.example.alpha.JavaFx.role_admin.model.SinhVien.KqMonHoc_SV;
import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class DiemQuaTrinhController implements Initializable{
    @FXML
    private VBox VBox_MonHoc;
    @FXML
    private ScrollPane ScrollPane_Main;
    @FXML
    private TextField textField_SearchMaGV;
    @FXML
    private TextField TextField_DiemQT;
    @FXML
    private TextField TextField_SearchMaSV;
    @FXML
    private Button Button_Update;
    @FXML
    private Button button_import;

    private List<PhancongEntity> phanCongList;

    //Gán mỗi pane MaMH để tìm kiếm
    private final Map<String, Pane> MaMH = new HashMap<>();

    //Lọc dữ liệu
    private final Map<String, String> GV_hocky = new HashMap<>();
    private final Map<String, String> GV_namhoc = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        phanCongList = PhanCong.getRepository().findAll();
        ScrollPane_Main.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //VBox tự động thay đổi chiều cao cho phù hợp với đối tượng vừa thêm vào
        VBox_MonHoc.setPrefHeight(Region.USE_COMPUTED_SIZE);

        addData();
        addListenerSearchMaMonHoc();
        addListenerSearchMaGV();
        addListenerSearchMaSV();

        //Khi năm, học kỳ thay đổi
        Singleton.getInstant().getViewFactory().getNamHoc().addListener((observable, oldValue, newValue) -> {
            addData();
        });
        Singleton.getInstant().getViewFactory().getHocky().addListener((observable, oldValue, newValue) -> {
            addData();
        });

        //Hiển thi điểm khi nhấn vào ô bảng DiemQT
        Singleton.getInstant().getDiemQuaTrinh().getDiem().addListener((observable, oldValue, newValue) -> {
            TextField_DiemQT.setText(newValue);
        });

        Button_Update.setOnAction(event -> {
            KqMonHoc_SV.getRepository().updateDiem(Singleton.getInstant().getDiemQuaTrinh().getMaSV().get(),
                    Singleton.getInstant().getDiemQuaTrinh().getMaMHSelected().get(),
                    Float.valueOf(TextField_DiemQT.getText()),
                    Singleton.getInstant().getViewFactory().getHocky().get(),
                    Singleton.getInstant().getViewFactory().getNamHoc().get());
            Singleton.getInstant().getDiemQuaTrinh().getDiem().set(TextField_DiemQT.getText());
            Singleton.getInstant().getDiemQuaTrinh().getIsUpdate().set(true);
            Singleton.getInstant().getDiemQuaTrinh().getIsUpdate().set(false);
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

            Singleton.getInstant().getDiemQuaTrinh().getReLoad().set(true);
            Singleton.getInstant().getDiemQuaTrinh().getReLoad().set(false);

        });

        Singleton.getInstant().getViewFactory().getTienTrinh().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case input_score, statistical -> {
                    button_import.setDisable(true);
                    Button_Update.setDisable(true);
                }
                case review -> {
                    button_import.setDisable(false);
                    Button_Update.setDisable(false);
                }
            }
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
                    KqMonHoc_SV.getRepository().updateDiem(
                            String.valueOf((int) sheet.getRow(rowIndex).getCell(3).getNumericCellValue()),
                            sheet.getRow(rowIndex).getCell(1).getStringCellValue(),
                            (float) sheet.getRow(rowIndex).getCell(4).getNumericCellValue(),
                            String.valueOf((int) sheet.getRow(rowIndex).getCell(0).getNumericCellValue()),
                            String.valueOf((int) sheet.getRow(rowIndex).getCell(2).getNumericCellValue()));
                }
                workbook.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void addData(){
        /*Xóa dữ liệu cũ trước khi cập nhật*/
        VBox_MonHoc.getChildren().clear();

        List<String> MonHoc = new ArrayList<>(Collections.emptyList());

        // Tải CellMonHoc, tương ứng với mỗi CellMonHoc là nhưng giáo viên được phân công dạy một đó
        for (PhancongEntity phancongEntity : phanCongList) {
            // Lấy MaMH xuất hiện lần đầu trong MaGV của CellGiaoVien
            if (!MonHoc.contains(phancongEntity.getMaMonHoc())
                    && Objects.equals(Singleton.getInstant().getViewFactory().getHocky().get(), phancongEntity.getMaHocKy())
                    && Objects.equals(phancongEntity.getMaNamHoc(), Singleton.getInstant().getViewFactory().getNamHoc().get())) {

                //Cập nhật thông tin trước khi tải CellMonHoc
                MonHoc.add(Singleton.getInstant().getCellGiaoVien().getMaMH().get());
                Singleton.getInstant().getCellGiaoVien().getMaMH().set(phancongEntity.getMaMonHoc());

                //Tải CellMonHoc
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/alpha/fxml/role_admin/DiemQuaTrinh/CellMonHoc.fxml"));
                AnchorPane pane;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //Tính độ cao của CellMonhoc
                double height = pane.getHeight();
                if (Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().get() >= 2) {
                    for (int k = 0; k < Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().get(); k++) {
                        pane.setPrefHeight(height + 48);
                        pane.setMinHeight(height + 48);
                        pane.setMaxHeight(height + 48);
                        height += 48;
                    }
                    Singleton.getInstant().getCellGiaoVien().getSlGiaoVien().set(1);
                }

                //Thêm vào VBox_GV
                VBox_MonHoc.getChildren().add(pane);


                MaMH.put(Singleton.getInstant().getCellGiaoVien().getMaMH().get(), pane);
                GV_hocky.put(phancongEntity.getMaMonHoc(), phancongEntity.getMaHocKy());
                GV_namhoc.put(phancongEntity.getMaMonHoc(), phancongEntity.getMaNamHoc());
            }
        }
        /*Reset lại MaGV khi thay đổi năm, học kỳ*/
        Singleton.getInstant().getCellGiaoVien().getMaGV().set("");
        Singleton.getInstant().getCellGiaoVien().getMaMH().set("");
    }

    private void addListenerSearchMaMonHoc(){
        //Tìm kiếm dựa trên MaMH
        textField_SearchMaGV.textProperty().addListener((observable, oldValue, newValue) -> {
            //Xóa dữ liệu trước khi tìm kiếm
            VBox_MonHoc.getChildren().clear();
            Singleton.getInstant().getDiemQuaTrinh().getSearch_GV().set(newValue);
            if(newValue.isBlank() || newValue.isEmpty())
                addData();
            else {
                MaMH.forEach((MonHoc, pane) -> {
                    if (GV_hocky.get(MonHoc).equals(Singleton.getInstant().getViewFactory().getHocky().get())
                            && GV_namhoc.get(MonHoc).equals(Singleton.getInstant().getViewFactory().getNamHoc().get())) {
                        if (newValue.equals(MonHoc) && !VBox_MonHoc.getChildren().contains(pane)) {
                            VBox_MonHoc.getChildren().add(pane);
                        }
                    }
                });
            }
        });
    }

    private void addListenerSearchMaGV(){
        //Tìm kiếm theo MaGV
        //getPane trả về MaMH tượng ứng với MaGV
        Singleton.getInstant().getDiemQuaTrinh().getPane().addListener((observable, oldValue, newValue) -> {
            MaMH.forEach((MonHoc, pane) -> {
                if (Singleton.getInstant().getDiemQuaTrinh().getPane().get() != null
                        && GV_hocky.get(MonHoc).equals(Singleton.getInstant().getViewFactory().getHocky().get())
                        && GV_namhoc.get(MonHoc).equals(Singleton.getInstant().getViewFactory().getNamHoc().get())) {
                    if (newValue.equals(MonHoc) && !VBox_MonHoc.getChildren().contains(pane)) {
                        VBox_MonHoc.getChildren().add(pane);
                    }
                }
            });
        });
    }

    private void addListenerSearchMaSV(){
        TextField_SearchMaSV.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!TextField_SearchMaSV.getText().isBlank()){
                Singleton.getInstant().getDiemQuaTrinh().getSearch_SV().set(newValue);
            }else {
                Singleton.getInstant().getDiemQuaTrinh().getSearch_SV().set("");
            }
        });
    }
}