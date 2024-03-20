package com.example.alpha.Spring_boot.result.student;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.result.DiemRenLuyenEntity;
import com.example.alpha.Spring_boot.result.HoclucEntity;
import com.example.alpha.Spring_boot.result.KetquaEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "KQ_HOCSINH_CANAM")
@IdClass(KqSinhVienCanamEntityPK.class)
public class KqSinhVienCanamEntity {
    @Id
    @Column(name = "MaSinhVien", length = 8, nullable = false)
    private String maSinhVien;

    @Id
    @Column(name = "MaLop")
    private String maLop;

    @Id
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Basic
    @Column(name = "DiemTBHK1")
    private double diemTbhk1;

    @Basic
    @Column(name = "DiemTBHK2")
    private double diemTbhk2;

    @Basic
    @Column(name = "DiemTBCN")
    private double diemTbcn;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaHocLuc")
    private HoclucEntity hoclucEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaHanhKiem")
    private DiemRenLuyenEntity hanhkiemEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaKetQua")
    private KetquaEntity ketquaEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaNamHoc", updatable = false, insertable = false)
    private NamhocEntity namhocEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaLop", insertable = false, updatable = false)
    private LopEntity lopEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaSinhVien", updatable = false, insertable = false)
    private SinhVienEntity hocsinhEntity;
}
