package com.example.alpha.Spring_boot.subject;

import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.result.PhongThiEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@IdClass(DiemThiPKEntity.class)
@Table(name = "DiemThi")
public class DiemThiEntity {
    @Id
    @Basic
    @Column(name = "MaSinhVien",
            length = 8,
            nullable = false
    )
    private String maSinhVien;

    @Id
    @Basic
    @Column(name = "MaMonHoc")
    private String maMonHoc;

    @Id
    @Basic
    @Column(name = "MaHocKy")
    private String maHocKy;

    @Id
    @Basic
    @Column(name = "MaNamHoc")
    private String maNamHoc;

    @Id
    @Basic
    @Column(name = "LanThi")
    private int lanThi;

    @Id
    @Column(name = "PhongThi")
    private String PhongThi;

    @Nullable
    @Column(name = "Diem")
    @Check(constraints = "diem BETWEEN 0 AND 10")
    private Float Diem;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MaSinhVien", insertable = false, updatable = false)
    private SinhVienEntity sinhVienEntity;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "MaMonHoc", updatable = false, insertable = false)
    private MonhocEntity monhocEntity;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "MaHocKy", updatable = false, insertable = false)
    private HockyEntity hockyEntity;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "PhongThi", insertable = false, updatable = false)
    private PhongThiEntity phongThiEntity;

    public DiemThiEntity(String maSinhVien, String maMonHoc, String maHocKy, String maNamHoc, int lanThi, Float Diem, String phongThi) {
        this.maSinhVien = maSinhVien;
        this.maMonHoc = maMonHoc;
        this.maHocKy = maHocKy;
        this.maNamHoc = maNamHoc;
        this.lanThi = lanThi;
        this.PhongThi = phongThi;
        this.Diem = Diem;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        return false;
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
