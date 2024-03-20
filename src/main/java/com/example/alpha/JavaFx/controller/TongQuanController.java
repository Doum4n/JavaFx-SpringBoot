package com.example.alpha.JavaFx.controller;

import com.example.alpha.JavaFx.DatabaseConnection.DatabaseConnection;
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

    public void setChart_TongQuan() throws SQLException {
        Chart_TongQuan.getData().clear();
        String sql = "select tenmh,sum(gia) from mat_hang group by tenmh";

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        XYChart.Series chart = new XYChart.Series();
        while (resultSet.next()){
            chart.getData().add(new XYChart.Data<>(resultSet.getString(1),resultSet.getInt(2)));
        }
        Chart_TongQuan.getData().add(chart);
    }
    public void setSLMatHang() throws SQLException {
//        Chart_TongQuan.getData().clear();
        String sql = "select count(mamh) from mat_hang";

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int sumMH = 0;
        while (resultSet.next()){
            sumMH = resultSet.getInt(1);
        }
        Label_SLMatHang.setText(String.valueOf(sumMH));
    }

    public void setSLKhachHang() throws SQLException {
        String sql = "select count(makh) from khach_hang";

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int sumKH = 0;
        while (resultSet.next()){
            sumKH = resultSet.getInt(1);
        }
        Label_SLKhachHang.setText(String.valueOf(sumKH));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* try {
            setChart_TongQuan();
            setSLKhachHang();
            setSLMatHang();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
