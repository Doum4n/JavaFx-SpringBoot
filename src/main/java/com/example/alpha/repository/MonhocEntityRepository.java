package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.MonhocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonhocEntityRepository extends JpaRepository<MonhocEntity, String> {
    @Query("select a.tenMonHoc from MonhocEntity a where a.maMonHoc=?1")
    String getTenMH(String MaMH);
}