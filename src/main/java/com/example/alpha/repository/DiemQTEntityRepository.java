package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import com.example.alpha.Spring_boot.subject.DiemQTPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DiemQTEntityRepository extends JpaRepository<DiemQTEntity, DiemQTPK> {
    @Query("select a.DiemQT from DiemQTEntity a where a.MaSV=?1 and a.MaMH=?2")
    Double getDiemQT(String MaSV, String MaMH);

    @Transactional
    @Modifying
    @Query("update DiemQTEntity a set a.DiemQT=?3 where a.MaSV=?1 and a.MaMH=?2")
    void updateDiemQT(String MaSV, String MaMH, double DiemQT);
}