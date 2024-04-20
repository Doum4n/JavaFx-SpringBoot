package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.student.KqSVMonHoc;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KqSVMonhocEntityRepository extends JpaRepository<KqSinhVienMonhocEntity, KqSinhVienMonhocEntityPK> {
    @Transactional
    @Modifying
    @Query("update KqSinhVienMonhocEntity a set a.diemQuaTrinh=?3 where a.maSinhVien=?1 and a.maMonHoc=?2")
    void updateDiem(String maSinhVien, String MaMH, Float diem);

    @Query("select new KqSinhVienMonhocEntity(a.maSinhVien,a.maMonHoc,a.maHocKy, a.maNamHoc, a.diemQuaTrinh) from KqSinhVienMonhocEntity a")
    List<KqSinhVienMonhocEntity> findAllExceptDiemThi();

    @Query("select a.diemQuaTrinh from KqSinhVienMonhocEntity a where a.maSinhVien=?1 and a.maMonHoc=?2")
    Float getDiemQT(String MaSV, String MaMH);

    @Transactional
    @Modifying
    @Query("update KqSinhVienMonhocEntity a set a.diemThi=?3 where a.maSinhVien=?1 and a.maMonHoc=?2 and a.maHocKy=?4 and a.maNamHoc=?5")
    void UpdateDiemThi(String maSinhVien, String MaMH, Float diem, String MaHocKy, String MaNamHoc);

    @Query("select a.diemThi from KqSinhVienMonhocEntity a where a.maSinhVien=?1 and a.maMonHoc=?2")
    Float getDiemThi(String MaSV, String MaMH);

    @Transactional
    @Modifying
    @Query("update KqSinhVienMonhocEntity a set a.DiemTK=?3 where a.maSinhVien=?1 and a.maMonHoc=?2")
    void UpdateDiemTK(String maSinhVien, String MaMH, Float diem);

    @Query("select a.DiemTK from KqSinhVienMonhocEntity a where a.maSinhVien=?1 and a.maMonHoc=?2 and a.maHocKy=?3 and a.maNamHoc=?4")
    Float getDiemTK(String MaSV, String MaMH, String MaHocKy, String MaNamHoc);

    @Query("select a.maMonHoc from KqSinhVienMonhocEntity a where a.maSinhVien=?1")
    String getMonHocByMaSV(String maSV);
}