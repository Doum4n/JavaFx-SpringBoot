package com.example.alpha.repository;

import com.example.alpha.Spring_boot.user.LoainguoidungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoainguoidungEntityRepository extends JpaRepository<LoainguoidungEntity, String> {
    @Query("select a.maLoai from LoainguoidungEntity a where a.tenLoai=?1")
    String getMaLoai(String TenLoai);
}