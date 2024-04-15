package com.example.alpha.Spring_boot.result.student;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class KqSinhVienHocKyPK implements Serializable {
    private String MaSinhVien;
    private String MaHocKy;
    private String MaNamHoc;
}
