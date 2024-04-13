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
}