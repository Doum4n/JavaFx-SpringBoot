package com.example.alpha.Spring_boot.class_grade;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "GIAOVIEN")
public class GiaovienEntity {
    @Id
    @Column(name = "MaGiaoVien")
    private String maGiaoVien;

    @Basic
    @Column(name = "TenGiaoVien")
    private String tenGiaoVien;

    @Basic
    @Column(name = "DiaChi")
    private String diaChi;

    @Basic
    @Column(name = "DienThoai")
    private String dienThoai;

    @Basic
    @Column(name = "MaMonHoc")
    private String maMonHoc;
}
