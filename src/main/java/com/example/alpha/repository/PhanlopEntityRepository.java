package com.example.alpha.repository;

import com.example.alpha.Spring_boot.assignment.PhanlopEntity;
import com.example.alpha.Spring_boot.assignment.PhanlopEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhanlopEntityRepository extends JpaRepository<PhanlopEntity, PhanlopEntityPK> {
    @Query("select a.maLop from PhanlopEntity a where a.maSinhVien=?1")
    String getLop(String MaSV);

    @Query("select a.maLop from PhanlopEntity a")
    List<String> getAllLop();
}