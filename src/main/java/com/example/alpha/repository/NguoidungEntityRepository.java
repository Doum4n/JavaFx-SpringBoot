package com.example.alpha.repository;

import com.example.alpha.Spring_boot.user.NguoidungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NguoidungEntityRepository extends JpaRepository<NguoidungEntity, String> {
    @Query("select a from NguoidungEntity a where a.maLoai='3'")
    List<NguoidungEntity> getSV();

    @Query("select a from NguoidungEntity a where a.maLoai='2'")
    List<NguoidungEntity> getGV();

    @Query("select a.tenDangNhap from NguoidungEntity a where a.tenDangNhap=?1")
    String getUsername(String username);

    @Query("select a.matKhau from NguoidungEntity a where a.matKhau=?2 and a.tenDangNhap=?1 and a.maLoai=?3")
    String getPassword(String username, String password, String AccountType);

    @Transactional
    @Modifying
    @Query("update NguoidungEntity a set a.matKhau=?3 where a.tenDangNhap=?1 and a.matKhau=?2 and a.maLoai=?4")
    void updatePassword(String username, String oldPassword, String newPassword, String AccountType);
}