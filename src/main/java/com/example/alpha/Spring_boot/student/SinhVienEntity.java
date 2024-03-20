package com.example.alpha.Spring_boot.student;

import com.example.alpha.Spring_boot.assignment.PhanlopEntity;
import com.example.alpha.Spring_boot.student.student_profile.DantocEntity;
import com.example.alpha.Spring_boot.student.student_profile.TongiaoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "SINHVIEN")
public class SinhVienEntity {
    @Id
    @Column(
            name = "MaSinhVien",
            nullable = false,
            length = 8
    )
    @NotNull
    private String maSinhVien;

    @Basic
    @Column(name = "HoTen")
    private String hoTen;


    @Basic
    @Column(name = "GioiTinh")
    private Boolean gioiTinh;


    @Basic
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Basic
    @Column(name = "DiaChi")
    private String diaChi;

    @Basic
    @Column(name = "Email")
    private String email;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaDanToc")
    private DantocEntity dantocEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaTonGiao")
    private TongiaoEntity tongiaoEntity;

    @OneToMany(mappedBy = "hocsinhEntities")
    @ToString.Exclude
    private Set<PhanlopEntity> phanlopEntity;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SinhVienEntity that = (SinhVienEntity) o;
        return getMaSinhVien() != null && Objects.equals(getMaSinhVien(), that.getMaSinhVien());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
