package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.assignment.PhanlopEntity;
import com.example.alpha.Spring_boot.assignment.PhanlopEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhanlopEntityRepository extends JpaRepository<PhanlopEntity, PhanlopEntityPK> {
}