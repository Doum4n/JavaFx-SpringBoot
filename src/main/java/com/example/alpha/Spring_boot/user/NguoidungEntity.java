package com.example.alpha.Spring_boot.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "NGUOIDUNG")
@NoArgsConstructor
public class NguoidungEntity {
    @Basic
    @Column(name = "MaLoai")
    private String maLoai;

    @Id
    @Basic
    @Column(name = "TenDangNhap") //MaSV || MaGV
    private String tenDangNhap;

    @Basic
    @Column(name = "MatKhau")
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "MaLoai", insertable = false, updatable = false)
    private LoainguoidungEntity loainguoidungEntity;

    public NguoidungEntity(String maLoai, String tenDangNhap, String matKhau) {
        this.maLoai = maLoai;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }
}
