package com.example.alpha.JavaFx.role_admin.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class QuanLyTaiKhoan {
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty AccountType;

    private final BooleanProperty isUpdate;
    private final BooleanProperty isDelete;
    private final BooleanProperty isCreate;

    private final StringProperty create_username;
    private final StringProperty create_password;
    private final StringProperty CreateAccountType;

    public QuanLyTaiKhoan(){
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        AccountType = new SimpleStringProperty();
        isUpdate = new SimpleBooleanProperty();
        isDelete = new SimpleBooleanProperty();
        isCreate = new SimpleBooleanProperty();
        CreateAccountType = new SimpleStringProperty();
        create_username = new SimpleStringProperty();
        create_password = new SimpleStringProperty();
    }
}
