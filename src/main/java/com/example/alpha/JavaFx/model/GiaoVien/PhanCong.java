package com.example.alpha.JavaFx.model.GiaoVien;

import com.example.alpha.repository.PhancongEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class PhanCong {
    private static PhancongEntityRepository repository = null;

    public PhanCong(PhancongEntityRepository repository){
        PhanCong.repository=repository;
    }

    public static PhancongEntityRepository getRepository() {
        return repository;
    }
}
