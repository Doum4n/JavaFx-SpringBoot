package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemEntity;
import javafx.beans.binding.DoubleExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    List<String> getPhongThi(String MaSV, String maMonHoc);

    @Query("select a.PhongThi from DiemEntity a where a.maMonHoc=?1")
    List<String> getPhongThiByMaMH(String MaMH);

    @Query("select a.lanThi from DiemEntity a where a.maMonHoc=?2 and a.maSinhVien=?1")
    List<String> getLanThi(String MaSV, String MaMH);

    @Transactional
    @Modifying
    @Query("update DiemEntity a set a.Diem=?3 where a.maSinhVien=?1 and a.maMonHoc=?2 and a.lanThi=?4")
    void updateDiem(String maSinhVien, String MaMH, String diem, int LanThi);

    @Query("select a.Diem from DiemEntity a where a.maSinhVien=?1 and a.maMonHoc=?2 and a.lanThi=?3")
    Double getDiem(String MaSV, String MaMH, String LanThi);

    @Query("select a.Diem from DiemEntity a where a.maSinhVien=?1 and a.maMonHoc=?2")
    List<Double> getDiems(String MaSV, String MaMH);
}