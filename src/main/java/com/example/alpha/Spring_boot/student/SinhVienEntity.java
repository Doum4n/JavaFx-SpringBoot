package com.example.alpha.Spring_boot.student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import javafx.scene.control.Button;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "SINHVIEN",uniqueConstraints = @UniqueConstraint(columnNames = "MaSinhVien"))
public class SinhVienEntity {
    @Id
    @Column(
            name = "MaSinhVien",
            nullable = false,
            length = 8
    )
    @NotNull
    private String maSinhVien;

    @Basic
    @Column(name = "HoTen")
    private String hoTen;


    @Basic
    @Column(name = "GioiTinh")
    private Boolean gioiTinh;


    @Basic
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Basic
    @Column(name = "DiaChi")
    private String diaChi;

    @Basic
    @Column(name = "Email")
    private String email;

    public SinhVienEntity(String maSinhVien, String hoTen, Boolean gioiTinh, Date ngaySinh, String diaChi, String email) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
    }
}
