package com.example.alpha.repository;

import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhancongEntityRepository extends JpaRepository<PhancongEntity, Integer> {
    @Query("select a.maHocKy from PhancongEntity a where a.maGiaoVien=?1")
    String getMaHocKy(String MaSV);
    @Query("select a.maNamHoc from PhancongEntity a where a.maGiaoVien=?1")
    List<String> getNamHoc(String MaSV);
}