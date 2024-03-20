package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.subject.MonhocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonhocEntityRepository extends JpaRepository<MonhocEntity, String> {
}