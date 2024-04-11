package com.example.alpha.JavaFx.model.GiaoVien;

import com.example.alpha.repository.GiaovienEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class GiaoVien {
    @Getter
    private static GiaovienEntityRepository repository;
    public GiaoVien(GiaovienEntityRepository repository){
        GiaoVien.repository = repository;
    }
}
