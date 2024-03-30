package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.DiemEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class Diem {
    private static DiemEntityRepository repository;
    public Diem(DiemEntityRepository repository) {
        Diem.repository = repository;
    }

    public static DiemEntityRepository getRepository() {
        return repository;
    }
}
