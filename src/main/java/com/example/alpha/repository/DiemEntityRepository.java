package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemThiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiemEntityRepository extends JpaRepository<DiemThiEntity, Integer> {
    @Transactional
    void deleteByMaSinhVien(String MaSinhVien);

    @Query("select a.lanThi from DiemThiEntity a where a.maSinhVien=?1")
    List<Integer> getLanThiByMaSV(String MaSV);

    @Query("select a.PhongThi from DiemThiEntity a where a.maSinhVien=?1 and a.maMonHoc=?2")
    List<String> getPhongThi(String MaSV, String maMonHoc);

    @Query("select a.PhongThi from DiemThiEntity a where a.maMonHoc=?1 and a.maHocKy=?2 and a.maNamHoc=?3")
    List<String> getPhongThiByMaMH(String MaMH, String MaHocky, String MaNamHoc);

    @Query("select a.lanThi from DiemThiEntity a where a.maMonHoc=?2 and a.maSinhVien=?1")
    List<String> getLanThi(String MaSV, String MaMH);

    @Transactional
    @Modifying
    @Query("update DiemThiEntity a set a.Diem=?3 where a.maSinhVien=?1 and a.maMonHoc=?2 and a.lanThi=?4 and a.maHocKy=?5 and a.maNamHoc=?6")
    void updateDiem(String maSinhVien, String MaMH, String diem, int LanThi, String MaHocKy, String MaNamHoc);

    @Query("select a.Diem from DiemThiEntity a where a.maSinhVien=?1 and a.maMonHoc=?2 and a.lanThi=?3 and a.maHocKy=?4 and a.maNamHoc=?5")
    Double getDiem(String MaSV, String MaMH, String LanThi, String MaHocKy, String MaNamHoc);

    @Query("select a.Diem from DiemThiEntity a where a.maSinhVien=?1 and a.maMonHoc=?2")
    List<Float> getDiems(String MaSV, String MaMH);

    @Query("select a.Diem from DiemThiEntity a where a.maSinhVien=?1 and a.maMonHoc=?2 and a.maHocKy=?3 and a.maNamHoc=?4 and a.lanThi=1")
    Float getDiemThiL1(String MaSV, String MaMH, String HocKy, String NamHoc);

    @Query("select a from DiemThiEntity a where a.maSinhVien=?1")
    List<DiemThiEntity> findAllbyMaSV(String MaSV);

    @Query("select a.maHocKy from DiemThiEntity a where a.maSinhVien=?1")
    List<String> getHocKyByMaSV(String MaSV);

    @Query("select a.maNamHoc from DiemThiEntity a where a.maSinhVien=?1")
    List<String> getNamHocByMaSV(String MaSV);
}