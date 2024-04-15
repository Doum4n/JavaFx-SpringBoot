package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.LopEntityRepository;
import jakarta.persistence.Column;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Lop {
    @Getter
    private static LopEntityRepository repository;

    public Lop(LopEntityRepository repository) {
        Lop.repository = repository;
    }
}
