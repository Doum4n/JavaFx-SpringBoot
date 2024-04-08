package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiemEntityRepository extends JpaRepository<DiemEntity, Integer> {
    @Transactional
    void deleteByMaSinhVien(String MaSinhVien);

    @Query("select a.lanThi from DiemEntity a where a.maSinhVien=?1")
    List<Integer> getLanThiByMaSV(String MaSV);

    @Query("select a.PhongThi from DiemEntity a where a.maSinhVien=?1 and a.maMonHoc=?2")
    String getPhongThi(String MaSV, String maMonHoc);

    @Query("select a.PhongThi from DiemEntity a where a.maMonHoc=?1")
    String getPhongThiByMaMH(String MaMH);

    @Query("select a.lanThi from DiemEntity a where a.maMonHoc=?2 and a.maSinhVien=?1")
    String getLanThi(String MaSV, String MaMH);
}