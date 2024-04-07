package com.example.alpha.Spring_boot.student;

import com.example.alpha.JavaFx.model.NamHoc;
import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "DangKyHocPhan")
@IdClass(DKHocPhanPK.class)
public class DKHocPhanEntity {
    @Id
    @Column(
            name = "MaSV",
            length = 8
    )
    @NotNull
    private String MaSV;
    @Id
    @Column(name = "MaMH")
    private String MaMH;
    @Id
    @Column(name = "MaHocKy")
    private String HocKy;
    @Id
    @Column(name = "MaNamHoc")
    private String NamHoc;

    @ManyToOne
    @JoinColumn(name = "MaSV", updatable = false,insertable = false)
    private SinhVienEntity sinhVienEntity;

    @OneToOne()
    @JoinColumn(name = "MaMH",insertable = false,updatable = false)
    private MonhocEntity monhocEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaNamHoc", updatable = false, insertable = false)
    private NamhocEntity namhocEntity;

    @OneToOne
    @JoinColumn(name = "MaHocKy", insertable = false, updatable = false)
    private HockyEntity hockyEntity;

}
