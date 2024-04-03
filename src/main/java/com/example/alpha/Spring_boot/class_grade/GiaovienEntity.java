package com.example.alpha.Spring_boot.class_grade;

import com.example.alpha.Spring_boot.subject.MonhocEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "GIAOVIEN")
public class GiaovienEntity {
    @Id
    @Column(name = "MaGiaoVien")
    private String maGiaoVien;

    @Basic
    @Column(name = "TenGiaoVien")
    private String tenGiaoVien;

    @Basic
    @Column(name = "DiaChi")
    private String diaChi;

    @Basic
    @Column(name = "DienThoai")
    private String dienThoai;
}
