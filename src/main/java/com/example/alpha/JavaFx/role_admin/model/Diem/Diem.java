package com.example.alpha.JavaFx.role_admin.model.Diem;

import com.example.alpha.repository.DiemEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Diem {
    @Getter
    private static DiemEntityRepository repository;
    public Diem(DiemEntityRepository repository) {
        Diem.repository = repository;
    }
}
