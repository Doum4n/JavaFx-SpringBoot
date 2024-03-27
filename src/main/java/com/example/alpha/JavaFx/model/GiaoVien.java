package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.GiaovienEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class GiaoVien {
    private static GiaovienEntityRepository repository;

    public GiaoVien(GiaovienEntityRepository giaovienEntityRepository) {
        GiaoVien.repository = giaovienEntityRepository;
    }

    public static GiaovienEntityRepository getRepository() {
        return repository;
    }
}
