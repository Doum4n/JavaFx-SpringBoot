package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamhocEntityRepository extends JpaRepository<NamhocEntity, String> {
}