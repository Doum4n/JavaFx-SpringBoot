package com.example.alpha.Spring_boot.result.grade;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.HockyEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "KQ_LOPHOC_MONHOC")
@IdClass(KqLophocMonhocEntityPK.class)
public class KqLophocMonhocEntity {
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
    @Column(name = "SoLuongDat")
    private int soLuongDat;

    @Basic
    @Column(name = "TiLe")
    private double tiLe;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaLop", insertable = false, updatable = false)
    private LopEntity lopEntity;

    @ManyToOne
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;

    @ManyToOne
    @JoinColumn(name = "MaHocKy", insertable = false, updatable = false)
    private HockyEntity hockyEntity;

    @ManyToOne
    @JoinColumn(name = "MaMonHoc", insertable = false, updatable = false)
    private MonhocEntity monhocEntity;

}
