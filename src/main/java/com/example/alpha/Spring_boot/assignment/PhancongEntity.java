package com.example.alpha.Spring_boot.assignment;

import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PHANCONG")
@IdClass(PhancongPK.class)
public class PhancongEntity {
    @Id
    @Basic
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Id
    @Basic
    @Column(name = "MaHocKy")
    private String maHocKy;

    @Id
    @Basic
    @Column(name = "MaLop")
    private String maLop;

    @Id
    @Basic
    @Column(name = "MaMonHoc")
    private String maMonHoc;

    @Id
    @Basic
    @Column(name = "MaGiaoVien")
    private String maGiaoVien;

    @ManyToOne
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;

    @ManyToOne
    @JoinColumn(name = "MaHocKy", updatable = false, insertable = false)
    private HockyEntity hockyEntity;

    @ManyToOne
    @JoinColumn(name = "MaLop",updatable = false, insertable = false)
    private LopEntity lopEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaMonHoc",insertable = false, updatable = false)
    private MonhocEntity monhocEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaGiaoVien",updatable = false, insertable = false)
    private GiaovienEntity giaovienEntity;
}
