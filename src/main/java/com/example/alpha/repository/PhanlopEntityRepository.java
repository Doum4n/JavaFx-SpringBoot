package com.example.alpha.repository;

import com.example.alpha.Spring_boot.assignment.PhanlopEntity;
import com.example.alpha.Spring_boot.assignment.PhanlopEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanlopEntityRepository extends JpaRepository<PhanlopEntity, PhanlopEntityPK> {
}