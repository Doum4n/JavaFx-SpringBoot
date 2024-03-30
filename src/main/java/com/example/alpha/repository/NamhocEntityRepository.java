package com.example.alpha.repository;

import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NamhocEntityRepository extends JpaRepository<NamhocEntity, String> {
    @Query("select a.tenNamHoc from NamhocEntity a")
    List<String> getNamHoc();

    @Query("select a.maNamHoc from NamhocEntity a where a.tenNamHoc=?1")
    String getMa(String name);
}