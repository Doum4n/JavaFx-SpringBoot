package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KqSVCanamEntityRepository extends JpaRepository<KqSinhVienCanamEntity, KqSinhVienCanamEntityPK> {
}