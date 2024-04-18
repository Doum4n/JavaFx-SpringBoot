package com.example.alpha.repository;

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

    @Query("select a from KqSinnhVienHocKy a join DiemRLSinhVien b on a.MaSinhVien=b.MaSinhVien where a.DiemTkHocKy>=7.0 and b.DiemRL>=65")
    List<KqSinnhVienHocKy> findAllSV_HocBong();

    @Query("select a.DiemTkHocKy from KqSinnhVienHocKy a where a.MaSinhVien=?1 and a.MaHocKy='1' and a.MaNamHoc=?2")
    Float getDiemTKHK1(String MaSV, String MaNamHoc);

    @Query("select a.DiemTkHocKy from KqSinnhVienHocKy a where a.MaSinhVien=?1 and a.MaHocKy='2' and a.MaNamHoc=?2")
    Float getDiemTKHK2(String MaSV, String MaNamHoc);
}