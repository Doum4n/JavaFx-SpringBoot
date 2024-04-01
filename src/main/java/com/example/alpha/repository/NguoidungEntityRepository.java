package com.example.alpha.repository;

import com.example.alpha.Spring_boot.user.NguoidungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NguoidungEntityRepository extends JpaRepository<NguoidungEntity, String> {
    @Query("select a from NguoidungEntity a where a.maLoai='3'")
    List<NguoidungEntity> getSV();

    @Query("select a from NguoidungEntity a where a.maLoai='2'")
    List<NguoidungEntity> getGV();
}