package com.example.alpha.Spring_boot.repository;

import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KqSVMonhocEntityRepository extends JpaRepository<KqSinhVienMonhocEntity, KqSinhVienMonhocEntityPK> {
}