package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemQTEntity;
import com.example.alpha.Spring_boot.subject.DiemQTPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiemQTEntityRepository extends JpaRepository<DiemQTEntity, DiemQTPK> {
}