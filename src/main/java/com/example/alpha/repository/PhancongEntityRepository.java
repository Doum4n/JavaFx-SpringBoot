package com.example.alpha.repository;

import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhancongEntityRepository extends JpaRepository<PhancongEntity, Integer> {
    @Query("select a.maHocKy from PhancongEntity a where a.maGiaoVien=?1 and a.maMonHoc=?2")
    String getMaHocKy(String MaGV, String MaMonHoc);
    @Query("select a.maNamHoc from PhancongEntity a where a.maGiaoVien=?1 and a.maMonHoc=?2")
    List<String> getNamHoc(String MaGV, String MaMonHoc);

    @Query("select a.maLop from PhancongEntity a where a.maGiaoVien=?1 and a.maHocKy=?2 and a.maNamHoc=?3")
    List<String> getLopByMaGV(String MaGV, String HocKy, String NamHoc);

    @Query("select a.maMonHoc from PhancongEntity a where a.maGiaoVien=?1 and a.maHocKy=?2 and a.maNamHoc=?3")
    String getMonHoc(String MaGV, String HocKy, String NamHoc);
}