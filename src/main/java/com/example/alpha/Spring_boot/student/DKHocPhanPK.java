package com.example.alpha.Spring_boot.student;

import lombok.Data;

import java.io.Serializable;

@Data
public class DKHocPhanPK implements Serializable {
    private String MaSV;
    private String MaMH;
    private String HocKy;
    private String NamHoc;
}
