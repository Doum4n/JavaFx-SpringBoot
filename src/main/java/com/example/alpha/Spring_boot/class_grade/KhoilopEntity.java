package com.example.alpha.Spring_boot.class_grade;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "KHOILOP")
public class KhoilopEntity {
    @Id
    @Column(name = "MaKhoiLop")
    private String maKhoiLop;

    @Basic
    @Column(name = "TenKhoiLop")
    private String tenKhoiLop;
}
