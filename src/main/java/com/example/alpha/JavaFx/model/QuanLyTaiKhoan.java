package com.example.alpha.JavaFx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class QuanLyTaiKhoan {
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty AccountType;

    public QuanLyTaiKhoan(){
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        AccountType = new SimpleStringProperty();
    }
}
