package com.example.alpha.Spring_boot.subject;

import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

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
    @Column(name = "SoTC")
    private int SoTC;

    @Basic
    @Column(name = "TyLeDiemQT")
    private int TyLeDiemQT;

    @Basic
    @Column(name = "Nam")
    private Nam nam;

    enum Nam{
        Nam1, Nam2, Nam3, Nam4
    }
}
