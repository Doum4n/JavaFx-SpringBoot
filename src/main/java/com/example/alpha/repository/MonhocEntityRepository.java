package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.MonhocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonhocEntityRepository extends JpaRepository<MonhocEntity, String> {
    @Query("select a.tenMonHoc from MonhocEntity a where a.maMonHoc=?1")
    String getTenMH(String MaMH);

    @Query("select a.TyLeDiemQT from MonhocEntity a where a.maMonHoc=?1")
    Integer getTyLeDiemQT(String maMonHoc);

    @Query("select a.SoTC from MonhocEntity a where a.maMonHoc=?1")
    Integer getSTC(String maMonHoc);

    @Query("select a.maMonHoc from MonhocEntity a")
    List<String> getAllMonHoc();
}