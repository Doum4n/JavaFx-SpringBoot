package com.example.alpha.repository;

import com.example.alpha.Spring_boot.class_grade.GiaovienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GiaovienEntityRepository extends JpaRepository<GiaovienEntity, String> {
    @Query("select a.tenGiaoVien from GiaovienEntity a where a.maGiaoVien=?1")
    String getTenGV(String TenGV);
}