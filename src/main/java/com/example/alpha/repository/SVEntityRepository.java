package com.example.alpha.repository;

import com.example.alpha.Spring_boot.student.SinhVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SVEntityRepository extends JpaRepository<SinhVienEntity, String> {
    List<SinhVienEntity> findAllByMaSinhVienIsContaining(String id);
}