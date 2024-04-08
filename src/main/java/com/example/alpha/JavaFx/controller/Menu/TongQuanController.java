package com.example.alpha.JavaFx.controller.Menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Controller
public class TongQuanController implements Initializable {
    @FXML
    private AreaChart<?, ?> Chart_TongQuan;
    @FXML
    protected Label Label_SLKhachHang;

    @FXML
    protected Label Label_SLMatHang;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
