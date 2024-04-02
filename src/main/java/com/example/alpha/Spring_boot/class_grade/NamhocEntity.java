package com.example.alpha.Spring_boot.class_grade;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "NAMHOC")
public class NamhocEntity {
    @Id
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Basic
    @Column(name = "TenNamHoc")
    private String tenNamHoc;
}
