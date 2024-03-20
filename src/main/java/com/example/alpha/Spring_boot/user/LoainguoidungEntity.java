package com.example.alpha.Spring_boot.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LOAINGUOIDUNG")
public class LoainguoidungEntity {
    @Id
    @Column(name = "MaLoai")
    private String maLoai;

    @Basic
    @Column(name = "TenLoai")
    private String tenLoai;
}
