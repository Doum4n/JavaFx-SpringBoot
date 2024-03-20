package com.example.alpha.Spring_boot.result.grade;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.HockyEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "KQ_LOPHOC_HOCKY")
@IdClass(KqLophocHockyEntityPK.class)
@Data
public class KqLophocHockyEntity {
    @Id
    @Column(name = "MaLop")
    private String maLop;

    @Id
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Id
    @Column(name = "MaHocKy")
    private String maHocKy;

    @Basic
    @Column(name = "SoLuongDat")
    private int soLuongDat;

    @Basic
    @Column(name = "TiLe")
    private double tiLe;

    @OneToOne
    @JoinColumn(name = "MaLop", insertable = false, updatable = false)
    private LopEntity lopEntity;

    @OneToOne
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;

    @OneToOne
    @JoinColumn(name = "MaHocKy", insertable = false, updatable = false)
    private HockyEntity hockyEntity;

}
