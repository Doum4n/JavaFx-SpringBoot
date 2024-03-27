package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.PhancongEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
