package com.example.alpha.Spring_boot.result.student;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.result.LanThiEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import com.example.alpha.Spring_boot.subject.MonhocEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Data
@Table(name = "KQ_HOCSINH_MONHOC")
@IdClass(KqSinhVienMonhocEntityPK.class)
@NoArgsConstructor
public class KqSinhVienMonhocEntity {
    @Id
    @Column(name = "MaSinhVien",length = 8, nullable = false)
    private String maSinhVien;

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
    @Nullable
    @Column(name = "DiemQuaTrinh")
    private double diemQuaTrinh;

    @Basic
    @Nullable
    @Column(name = "DiemThi")
    private double diemThi;

    public KqSinhVienMonhocEntity(String maSinhVien, String maMonHoc, String maHocKy, String maNamHoc, double diemThi) {
        this.maSinhVien = maSinhVien;
        this.maNamHoc = maNamHoc;
        this.maMonHoc = maMonHoc;
        this.maHocKy = maHocKy;
        this.diemThi = diemThi;
    }

    @OneToOne
    @JoinColumn(name = "MaSinhVien", updatable = false, insertable = false)
    private SinhVienEntity hocsinhEntity;

    @OneToOne
    @JoinColumn(name = "MaNamHoc", updatable = false, insertable = false)
    private NamhocEntity namhocEntity;

    @OneToOne
    @JoinColumn(name = "MaMonHoc", updatable = false, insertable = false)
    private MonhocEntity monhocEntity;

    @OneToOne
    @JoinColumn(name = "MaHocKy", updatable = false, insertable = false)
    private HockyEntity hockyEntity;
}
