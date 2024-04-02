package com.example.alpha.repository;

import com.example.alpha.Spring_boot.assignment.PhancongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhancongEntityRepository extends JpaRepository<PhancongEntity, Integer> {
}