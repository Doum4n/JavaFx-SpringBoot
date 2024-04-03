package com.example.alpha.Spring_boot.subject;

import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "monhoc")
public class MonhocEntity {
    @Id
    @Column(name = "MaMonHoc")
    private String maMonHoc;

    @Basic
    @Column(name = "TenMonHoc")
    private String tenMonHoc;

    @Basic
    @Column(name = "SoTiet")
    private int soTiet;

    @Basic
    @Column(name = "HeSo")
    private int heSo;
}
