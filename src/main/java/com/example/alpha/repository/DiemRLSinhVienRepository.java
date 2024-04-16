package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.DiemRLSinhVien;
import com.example.alpha.Spring_boot.result.DiemRLSinhVienEntityPK;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiemRLSinhVienRepository extends JpaRepository<DiemRLSinhVien, Integer> {
    @Query("select a.DiemRL from DiemRLSinhVien a where a.MaSinhVien=?1 and a.MaHocKy=?2 and a.MaNamHoc=?3")
    Integer getDiemRenLuyen(String MaSV, String MaHocKy, String MaNamHoc);
}