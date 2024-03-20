package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.class_grade.LopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LopEntityRepository extends JpaRepository<LopEntity, String> {
}