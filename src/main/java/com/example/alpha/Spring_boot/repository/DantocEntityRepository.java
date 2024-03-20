package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.student.student_profile.DantocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DantocEntityRepository extends JpaRepository<DantocEntity, String> {
}