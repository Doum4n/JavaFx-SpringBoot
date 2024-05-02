package com.example.alpha.repository;

import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HockyEntityRepository extends JpaRepository<HockyEntity, String> {
    @Query("SELECT a.tenHocKy from HockyEntity a")
    List<String> getHocKy();
    @Query("select a.maHocKy from HockyEntity a where a.tenHocKy=?1")
    String getMa(String name);

    @Query("select a.tenHocKy from HockyEntity a where a.maHocKy=?1")
    String getTenHocKy(String MaHocKy);
}