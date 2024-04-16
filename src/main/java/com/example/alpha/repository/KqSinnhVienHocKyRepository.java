package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinnhVienHocKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KqSinnhVienHocKyRepository extends JpaRepository<KqSinnhVienHocKy, String> {
    @Modifying
    @Query("select new KqSinnhVienHocKy(a.MaSinhVien, a.MaHocKy, a.MaNamHoc) from KqSinnhVienHocKy a")
    List<KqSinnhVienHocKy> findAllExceptDiem();

    @Query("select a.DiemTkHocKy from KqSinnhVienHocKy a where a.MaSinhVien=?1 and a.MaHocKy=?2 and a.MaNamHoc=?3")
    Float getDiemTK(String MaSV, String MaHocKy, String MaNamHoc);

    @Transactional
    @Modifying
    @Query("update KqSinnhVienHocKy a set a.DiemTkHocKy=?4 where a.MaSinhVien=?1 and a.MaHocKy=?2 and a.MaNamHoc=?3")
    void updateDiemTK(String MaSV, String MaHocKy, String MaNamHoc, Float DiemTkHocKy);

    @Query("select a.DoTC from KqSinnhVienHocKy a where a.MaSinhVien=?1 and a.MaHocKy=?2 and a.MaNamHoc=?3")
    Float getTongTC(String MaSV, String MaHocKy, String MaNamHoc);

    @Transactional
    @Modifying
    @Query("update KqSinnhVienHocKy a set a.DoTC=?4 where a.MaSinhVien=?1 and a.MaHocKy=?2 and a.MaNamHoc=?3")
    void updateTongTC(String MaSV, String MaHocKy, String MaNamHoc, int SoTC);
}