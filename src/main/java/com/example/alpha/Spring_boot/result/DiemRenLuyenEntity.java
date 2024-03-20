package com.example.alpha.Spring_boot.result;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
