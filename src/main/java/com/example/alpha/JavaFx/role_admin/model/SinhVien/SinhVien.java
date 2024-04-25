package com.example.alpha.JavaFx.role_admin.model.SinhVien;

import com.example.alpha.repository.SVEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class SinhVien {
    private static SVEntityRepository svEntityRepository;

    public SinhVien(SVEntityRepository svEntityRepository) {
        SinhVien.svEntityRepository = svEntityRepository;
    }

    public static SVEntityRepository getRepository(){
        return svEntityRepository;
    }
}
