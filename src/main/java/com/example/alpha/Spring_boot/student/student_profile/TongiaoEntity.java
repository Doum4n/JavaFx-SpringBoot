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
@Table(name = "TONGIAO")
public class TongiaoEntity {
    @Id
    @Column(name = "MaTonGiao")
    private String maTonGiao;
    @Basic
    @Column(name = "TenTonGiao")
    private String tenTonGiao;

    @OneToMany(mappedBy = "tongiaoEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<SinhVienEntity> hocsinhEntities = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TongiaoEntity that = (TongiaoEntity) o;
        return getMaTonGiao() != null && Objects.equals(getMaTonGiao(), that.getMaTonGiao());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
