package com.example.alpha.repository;

import com.example.alpha.Spring_boot.student.DKHocPhanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DKHocPhanEntityRepository extends JpaRepository<DKHocPhanEntity, String> {
    @Query("select a.MaMH from  DKHocPhanEntity a where a.MaSV=?1")
    List<String> getMonhocDK(String MaSV);
}