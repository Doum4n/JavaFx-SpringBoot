package com.example.alpha.Spring_boot.assignment;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "PHANLOP")
@IdClass(PhanlopEntityPK.class)
public class PhanlopEntity {
    @Id
    @Column(name = "MaNamHoc", nullable = false)
    private String maNamHoc;

    @Id
    @Column(name = "MaLop", nullable = false)
    private String maLop;

    @Id
    @Column(name = "MaSinhVien", nullable = false, length = 8)
    private String maSinhVien;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaLop", insertable = false, updatable = false)
    private LopEntity lopEntity;

    @OneToOne
    @JoinColumn(name = "MaSinhVien",updatable = false,insertable = false)
    private SinhVienEntity hocsinhEntities;
}
