package com.example.alpha.Spring_boot.result;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "KETQUA")
public class KetquaEntity {
    @Id
    @Column(name = "MaKetQua")
    private String maKetQua;

    @Basic
    @Column(name = "TenKetQua")
    private String tenKetQua;
}
