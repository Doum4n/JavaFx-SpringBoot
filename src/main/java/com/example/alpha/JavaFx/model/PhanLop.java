package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.PhanlopEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class PhanLop {
    @Getter
    private static PhanlopEntityRepository repository;
    public PhanLop(PhanlopEntityRepository repository){
        PhanLop.repository = repository;
    }
}
