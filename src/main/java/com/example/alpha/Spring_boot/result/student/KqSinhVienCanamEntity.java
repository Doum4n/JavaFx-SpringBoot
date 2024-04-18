package com.example.alpha.Spring_boot.result.student;

import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Data
@Table(name = "KQ_HOCSINH_CANAM")
@IdClass(KqSinhVienCanamEntityPK.class)
@NoArgsConstructor
public class KqSinhVienCanamEntity {
    @Id
    @Column(name = "MaSinhVien", length = 8, nullable = false)
    private String maSinhVien;

    @Id
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Basic
    @Column(name = "DiemTBHK1")
    @Nullable
    private Float diemTbhk1;

    @Basic
    @Column(name = "DiemTBHK2")
    @Nullable
    private Float diemTbhk2;

    @Basic
    @Column(name = "DiemTBCN")
    @Nullable
    private Float diemTbcn;


    @OneToOne
    @JoinColumn(name = "MaNamHoc", updatable = false, insertable = false)
    private NamhocEntity namhocEntity;


    @OneToOne
    @JoinColumn(name = "MaSinhVien", updatable = false, insertable = false)
    private SinhVienEntity hocsinhEntity;

    public KqSinhVienCanamEntity(String maSinhVien, String maNamHoc) {
        this.maSinhVien = maSinhVien;
        this.maNamHoc = maNamHoc;
    }
}
