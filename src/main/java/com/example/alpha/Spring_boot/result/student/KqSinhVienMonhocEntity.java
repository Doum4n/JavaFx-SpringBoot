package com.example.alpha.Spring_boot.result.student;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "KQ_HOCSINH_MONHOC")
@IdClass(KqSinhVienMonhocEntityPK.class)
public class KqSinhVienMonhocEntity {
    @Id
    @Column(name = "MaSinhVien",length = 8, nullable = false)
    private String maSinhVien;

    @Id
    @Column(name = "MaLop")
    private String maLop;

    @Id
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Id
    @Column(name = "MaMonHoc")
    private String maMonHoc;

    @Id
    @Column(name = "MaHocKy")
    private String maHocKy;

    @Basic
    @Column(name = "DiemQuaTrinh")
    private double diemQuaTrinh;

    @Basic
    @Column(name = "DiemThi")
    private double diemThi;

    @Basic
    @Column(name = "DiemTBHK")
    private double diemTbhk;

    @OneToOne
    @JoinColumn(name = "MaSinhVien", updatable = false, insertable = false)
    private SinhVienEntity hocsinhEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaNamHoc", updatable = false, insertable = false)
    private NamhocEntity namhocEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaLop", updatable = false, insertable = false)
    private LopEntity lopEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaMonHoc", updatable = false, insertable = false)
    private MonhocEntity monhocEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaHocKy", updatable = false, insertable = false)
    private HockyEntity hockyEntity;
}
