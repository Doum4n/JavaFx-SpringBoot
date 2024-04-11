package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import com.example.alpha.Spring_boot.subject.DiemQTPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DiemQTEntityRepository extends JpaRepository<DiemQTEntity, DiemQTPK> {
    @Query("select a.DiemQT from DiemQTEntity a where a.MaSV=?1")
    Double getDiemQT(String MaSV);

    @Transactional
    @Modifying
    @Query("update DiemQTEntity a set a.DiemQT=?2 where a.MaSV=?1")
    void updateDiemQT(String MaSV, double DiemQT);
}