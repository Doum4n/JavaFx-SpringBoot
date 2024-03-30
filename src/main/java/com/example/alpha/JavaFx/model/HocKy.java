package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.HockyEntityRepository;
import com.example.alpha.repository.NamhocEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class HocKy {
    private static HockyEntityRepository repository;

    public HocKy(HockyEntityRepository repository) {
        HocKy.repository = repository;
    }

    public static HockyEntityRepository getRepository() {
        return repository;
    }
}
