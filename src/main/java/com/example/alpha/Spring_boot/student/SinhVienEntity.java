package com.example.alpha.Spring_boot.student;

import com.example.alpha.Spring_boot.student.student_profile.DantocEntity;
import com.example.alpha.Spring_boot.student.student_profile.TongiaoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "SINHVIEN",uniqueConstraints = @UniqueConstraint(columnNames = "MaSinhVien"))
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

    @Basic
    @Column(name = "MaDanToc")
    private String MaDanToc;

    @Basic
    @Column(name = "MaTonGiao")
    private String MaTonGiao;


    @ManyToOne
    @JoinColumn(name = "MaDanToc",insertable = false, updatable = false)
    private DantocEntity dantocEntity;

    @ManyToOne
    @JoinColumn(name = "MaTonGiao", updatable = false, insertable = false)
    private TongiaoEntity tongiaoEntity;

    public SinhVienEntity(String maSinhVien, String hoTen, Boolean gioiTinh, Date ngaySinh, String diaChi, String email, String maDanToc, String maTonGiao) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        MaDanToc = maDanToc;
        MaTonGiao = maTonGiao;
    }

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
