package com.example.alpha.Spring_boot.subject;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.result.LanThiEntity;
import com.example.alpha.Spring_boot.result.LoaidiemEntity;
import com.example.alpha.Spring_boot.student.HockyEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@IdClass(DiemPKEntity.class)
@Table(name = "DIEM")
public class DiemEntity {
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
    @Column(name = "MaLop")
    private String maLop;

    @Id
    @Basic
    @Column(name = "MaLoai")
    private String maLoai;

    @Basic
    @Column(name = "Diem")
    @Check(constraints = "diem BETWEEN 0 AND 10")
    private double diem;

    @Id
    @Column(name = "LanThi")
    private int lanThi;


    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaNamHoc", insertable = false, updatable = false)
    private NamhocEntity namhocEntity;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MaSinhVien", insertable = false, updatable = false)
    private SinhVienEntity sinhVienEntity;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MaMonHoc", updatable = false, insertable = false)
    private MonhocEntity monhocEntity;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaLop", insertable = false, updatable = false)
    private LopEntity lopEntity;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaLoai", updatable = false, insertable = false)
    private LoaidiemEntity loaidiemEntity;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "LanThi", insertable = false, updatable = false)
    private LanThiEntity lanThiEntity;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MaHocKy", updatable = false, insertable = false)
    private HockyEntity hockyEntity;

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
