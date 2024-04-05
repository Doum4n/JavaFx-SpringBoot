package com.example.alpha.repository;

import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhancongEntityRepository extends JpaRepository<PhancongEntity, Integer> {
    @Query("select a.maGiaoVien from PhancongEntity a where a.maLop=?1 and a.maMonHoc=?2")
    String getMaGV(String MaLop, String MaMH);
}