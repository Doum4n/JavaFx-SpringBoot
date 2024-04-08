package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.LanThiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanThiEntityRepository extends JpaRepository<LanThiEntity, Integer> {
    @Query("select a.LanThi from LanThiEntity a")
    List<String> getLanThi();
}