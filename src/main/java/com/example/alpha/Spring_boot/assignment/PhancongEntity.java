package com.example.alpha.Spring_boot.assignment;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PHANCONG")
public class PhancongEntity {
    @Id
    @Column(name = "STT")
    private int stt;

    @Basic
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Basic
    @Column(name = "MaLop")
    private String maLop;

    @Basic
    @Column(name = "MaMonHoc")
    private String maMonHoc;

    @Basic
    @Column(name = "MaGiaoVien")
    private String maGiaoVien;
}
