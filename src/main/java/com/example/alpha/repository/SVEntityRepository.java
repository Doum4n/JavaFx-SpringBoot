package com.example.alpha.repository;

import com.example.alpha.Spring_boot.student.SinhVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SVEntityRepository extends JpaRepository<SinhVienEntity, String> {
}