package com.example.alpha.repository;

import com.example.alpha.Spring_boot.result.grade.KqLophocMonhocEntity;
import com.example.alpha.Spring_boot.result.grade.KqLophocMonhocEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface KqLophocMonhocEntityRepository extends JpaRepository<KqLophocMonhocEntity, KqLophocMonhocEntityPK> {
}