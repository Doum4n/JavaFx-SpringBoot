package com.example.alpha.Spring_boot.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "NGUOIDUNG")
public class NguoidungEntity {
    @Id
    @Column(name = "MaNguoiDung")
    private String maNguoiDung;

    @Basic
    @Column(name = "MaLoai")
    private String maLoai;

    @Basic
    @Column(name = "TenNguoiDung")
    private String tenNguoiDung;

    @Basic
    @Column(name = "TenDangNhap")
    private String tenDangNhap;

    @Basic
    @Column(name = "MatKhau")
    private String matKhau;
}
