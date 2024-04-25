package com.example.alpha.JavaFx.role_admin.model.GiaoVien;

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
