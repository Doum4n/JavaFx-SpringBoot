package com.example.alpha.Spring_boot.result;

import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(DiemRLSinhVienEntityPK.class)
public class DiemRLSinhVien {
    @Id
    @Basic
    @Column(name = "MaSinhVien", nullable = false, length = 8)
    private String MaSinhVien;

    @Column(name = "DiemRL")
    private int DiemRL;

    @Id
    @Column(name = "MaHocKy")
    private String MaHocKy;

    @Id
    @Column(name = "MaNamHoc")
    private String MaNamHoc;

    @OneToOne
    @JoinColumn(name = "MaSinhVien", insertable = false, updatable = false)
    private SinhVienEntity sinhVienEntity;

    @OneToOne
    @JoinColumn(name = "MaHocKy",insertable = false, updatable = false)
    private HockyEntity hockyEntity;

    @OneToOne
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;
}
