package com.example.alpha.Spring_boot.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "NGUOIDUNG")
public class NguoidungEntity {
    @Id
    @Column(name = "MaNguoiDung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String maNguoiDung;

    @Basic
    @Column(name = "MaLoai")
    private String maLoai;

    @Basic
    @Column(name = "TenDangNhap") //MaSV || MaGV
    private String tenDangNhap;

    @Basic
    @Column(name = "MatKhau")
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "MaLoai", insertable = false, updatable = false)
    private LoainguoidungEntity loainguoidungEntity;

}
