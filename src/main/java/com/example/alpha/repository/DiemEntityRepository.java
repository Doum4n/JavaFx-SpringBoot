package com.example.alpha.repository;

import com.example.alpha.Spring_boot.subject.DiemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiemEntityRepository extends JpaRepository<DiemEntity, Integer> {
    @Transactional
    void deleteByMaSinhVien(String MaSinhVien);
}