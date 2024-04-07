package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntityPK;
import com.example.alpha.Spring_boot.subject.DiemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KqSVMonhocEntityRepository extends JpaRepository<KqSinhVienMonhocEntity, KqSinhVienMonhocEntityPK> {
//    @Query("select kq,diem from KqSinhVienMonhocEntity kq join DiemEntity diem on kq.maSinhVien=diem.maSinhVien")
//    List<KqSinhVienMonhocEntity> findAll();

    @Query("update KqSinhVienMonhocEntity a set a.diemThi=?2 where a.maSinhVien=?1")
    KqSinhVienMonhocEntity updateDiem(String maSinhVien, Double diem);
}