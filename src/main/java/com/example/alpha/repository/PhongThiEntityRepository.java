package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.PhongThiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhongThiEntityRepository extends JpaRepository<PhongThiEntity, String> {
    @Query("select a.maPhongThi from PhongThiEntity a")
    List<String> getMa();
}