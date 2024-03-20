package com.example.alpha.Spring_boot.class_grade;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LOP")
public class LopEntity {
    @Id
    @Column(name = "MaLop")
    private String maLop;
    @Basic
    @Column(name = "TenLop")
    private String tenLop;
    @Basic
    @Column(name = "MaKhoiLop")
    private String maKhoiLop;
    @Basic
    @Column(name = "MaNamHoc")
    private String maNamHoc;
    @Basic
    @Column(name = "SiSo")
    private int siSo;
    @Basic
    @Column(name = "MaGiaoVien")
    private String maGiaoVien;
}
