package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.SVEntityRepository;
import com.example.alpha.Spring_boot.student.SinhVienEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

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
