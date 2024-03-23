package com.example.alpha.repository;

import com.example.alpha.Spring_boot.class_grade.HockyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HockyEntityRepository extends JpaRepository<HockyEntity, String> {
}