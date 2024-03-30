package com.example.alpha.Spring_boot.subject;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiemPKEntity implements Serializable {
    private String maSinhVien;
    private String maMonHoc;
    private String maHocKy;
    private String maNamHoc;
    private int lanThi;
    private String PhongThi;
}
