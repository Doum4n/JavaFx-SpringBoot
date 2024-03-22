package com.example.alpha.Spring_boot.assignment;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhanlopEntityPK implements Serializable {
    private String maNamHoc;
    private String maLop;
    private String maSinhVien;
}
