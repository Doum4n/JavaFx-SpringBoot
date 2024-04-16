package com.example.alpha.Spring_boot.result;

import com.example.alpha.Spring_boot.student.SinhVienEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "DiemRenLuyen")
public class DiemRenLuyenEntity {
    @Id
    @Column(name = "MaDRL")
    private String maDRL;

    @Basic
    @Column(name = "Loai")
    private String Loai;
}
