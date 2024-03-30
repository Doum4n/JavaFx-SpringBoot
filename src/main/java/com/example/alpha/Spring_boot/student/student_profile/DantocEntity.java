/*
package com.example.alpha.Spring_boot.student.student_profile;

import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "DANTOC")
public class DantocEntity {
    @Id
    @Column(name = "MaDanToc", nullable = false)
    private String maDanToc;

    @OneToMany(mappedBy = "dantocEntity")
    @ToString.Exclude
    private Set<SinhVienEntity> hocsinhEntities = new LinkedHashSet<>();

    @Basic
    @Column(name = "TenDanToc")
    private String tenDanToc;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DantocEntity that = (DantocEntity) o;
        return getMaDanToc() != null && Objects.equals(getMaDanToc(), that.getMaDanToc());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
*/
