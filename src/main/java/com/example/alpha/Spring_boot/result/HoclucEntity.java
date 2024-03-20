package com.example.alpha.Spring_boot.result;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "HOCLUC")
public class HoclucEntity {
    @Id
    @Column(name = "MaHocLuc")
    private String maHocLuc;

    @Basic
    @Column(name = "TenHocLuc")
    private String tenHocLuc;

    @Basic
    @Column(name = "DiemCanDuoi")
    private double diemCanDuoi;

    @Basic
    @Column(name = "DiemCanTren")
    private double diemCanTren;

    @Basic
    @Column(name = "DiemKhongChe")
    private double diemKhongChe;
}
