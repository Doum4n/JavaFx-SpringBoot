package com.example.alpha.Spring_boot.result.student;

import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "kq_sinhvien_hocky")
@IdClass(KqSinhVienHocKyPK.class)
public class KqSinnhVienHocKy {
    @Id
    @Column(
            name = "MaSinhVien",
            nullable = false,
            length = 8
    )
    private String MaSinhVien;
    @Id
    @Column(name = "MaHocKy")
    private String MaHocKy;
    @Id
    @Column(name = "MaNamHoc")
    private String MaNamHoc;

    @Column(name = "DiemTkHocKy")
    private Float DiemTkHocKy;

    @Column(name = "SoTinChi")
    private int DoTC;

    @ManyToOne
    @JoinColumn(name = "MaSinhVien", insertable = false, updatable = false)
    private SinhVienEntity sinhVienEntity;

    @OneToOne
    @JoinColumn(name = "MaHocKy", insertable = false, updatable = false)
    private HockyEntity hockyEntity;

    @ManyToOne
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;
}
