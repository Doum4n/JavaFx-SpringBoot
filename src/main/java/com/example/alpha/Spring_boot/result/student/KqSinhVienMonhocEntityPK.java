package com.example.alpha.Spring_boot.result.student;

import lombok.Data;

import java.io.Serializable;

@Data
public class KqSinhVienMonhocEntityPK implements Serializable {
    private String maSinhVien;
    private String maNamHoc;
    private String maMonHoc;
    private String maHocKy;
}
