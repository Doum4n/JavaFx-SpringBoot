package com.example.alpha.repository;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LopEntityRepository extends JpaRepository<LopEntity, String> {
    @Query("select a.maLop from LopEntity a")
    List<String> getAllLop();

    @Query("select a.maLop from LopEntity a where year(:currentDate) - a.maNamHoc = 4")
    List<String> getNamCuoi();
}