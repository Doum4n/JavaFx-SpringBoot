package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface KqSVCanamEntityRepository extends JpaRepository<KqSinhVienCanamEntity, KqSinhVienCanamEntityPK> {
    @Modifying
    @Query("update KqSinhVienCanamEntity a set a.diemTbhk1=?2 where a.maSinhVien=?1 and a.maNamHoc=?3")
    void UpdateDiemHK1(String MaSV, Float DiemTBHK1, String MaNamHoc);

    @Modifying
    @Query("update KqSinhVienCanamEntity a set a.diemTbhk2=?2 where a.maSinhVien=?1 and a.maNamHoc=?3")
    void UpdateDiemHK2(String MaSV, Float DiemTBHK2, String MaNamHoc);

    @Modifying
    @Query("update KqSinhVienCanamEntity a set a.diemTbcn=?2 where a.maSinhVien=?1 and a.maNamHoc=?3")
    void UpdateDiemCaNam(String MaSV, Float DiemTBCaNam, String MaNamHoc);
}