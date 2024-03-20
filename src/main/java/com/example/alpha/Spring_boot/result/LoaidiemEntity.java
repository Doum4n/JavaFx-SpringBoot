package com.example.alpha.Spring_boot.result;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LOAIDIEM")
public class LoaidiemEntity {
    @Id
    @Column(name = "MaLoai")
    private String maLoai;

    @Basic
    @Column(name = "TenLoai")
    private String tenLoai;

    @Basic
    @Column(name = "HeSo")
    private int heSo;
}
