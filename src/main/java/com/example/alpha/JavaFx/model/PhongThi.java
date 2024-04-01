package com.example.alpha.JavaFx.model;

import com.example.alpha.Spring_boot.result.PhongThiEntity;
import com.example.alpha.repository.PhongThiEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class PhongThi {
    @Getter
    private static PhongThiEntityRepository repository;

    public PhongThi(PhongThiEntityRepository repository) {
        PhongThi.repository = repository;
    }

}
