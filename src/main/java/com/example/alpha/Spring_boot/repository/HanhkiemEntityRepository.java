package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.result.DiemRenLuyenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HanhkiemEntityRepository extends JpaRepository<DiemRenLuyenEntity, String> {
}