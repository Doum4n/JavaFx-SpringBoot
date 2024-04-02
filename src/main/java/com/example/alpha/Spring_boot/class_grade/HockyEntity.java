package com.example.alpha.Spring_boot.class_grade;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@jakarta.persistence.Table(name = "HOCKY")
public class HockyEntity {
    @Id
    @Column(name = "MaHocKy")
    private String maHocKy;

    @Basic
    @Column(name = "TenHocKy")
    private String tenHocKy;
}
