package com.example.alpha.JavaFx.Teacher;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class viewTeacher {
    private BorderPane showTeacher;

    public BorderPane getShowTeacher() {
        try {
            if (showTeacher == null) {
                showTeacher = new FXMLLoader(getClass().getResource("/com/example/alpha/GiaoVienRole/Teacher.fxml")).load();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return showTeacher;
    }
}
