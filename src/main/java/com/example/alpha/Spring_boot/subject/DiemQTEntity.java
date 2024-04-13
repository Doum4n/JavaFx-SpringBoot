package com.example.alpha.Spring_boot.subject;

import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DiemQTPK.class)
public class DiemQTEntity {
    @Id
    @Column(
            name = "MaSV",
            nullable = false,
            length = 8
    )
    @NotNull
    private String MaSV;
    @Id
    @Column(name = "MaMH")
    private String MaMH;
    @Id
    @Column(name = "MaHK")
    private String MaHK;
    @Id
    @Column(name = "MaNH")
    protected String MaNH;
    @Column(name = "DiemQT")
    private Double DiemQT;

    @ManyToOne
    @JoinColumn(name = "MaSV",updatable = false,insertable = false)
    private SinhVienEntity sinhVienEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaMH",insertable = false,updatable = false)
    private MonhocEntity monhocEntity;

    @OneToOne
    @JoinColumn(name = "MaHK",updatable = false,insertable = false)
    private HockyEntity hockyEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "MaNH",insertable = false,updatable = false)
    private NamhocEntity namhocEntity;

    public DiemQTEntity(String maSV, String maMH, String maHK, String maNH, Double diemQT) {
        this.MaSV = maSV;
        this.MaMH = maMH;
        this.MaHK = maHK;
        this.MaNH = maNH;
        this.DiemQT = diemQT;
    }
}
