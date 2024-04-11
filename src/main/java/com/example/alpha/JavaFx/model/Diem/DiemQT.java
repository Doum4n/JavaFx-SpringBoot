package com.example.alpha.JavaFx.model.Diem;

import com.example.alpha.repository.DiemQTEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DiemQT {
    @Getter
    private static DiemQTEntityRepository repository;
    public DiemQT(DiemQTEntityRepository repository){
        DiemQT.repository = repository;
    }
}
