package com.example.alpha.JavaFx.model;

import com.example.alpha.Spring_boot.class_grade.NamhocEntity;
import com.example.alpha.repository.NamhocEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NamHoc {
    private static NamhocEntityRepository repository;

    public NamHoc(NamhocEntityRepository repository) {
        NamHoc.repository = repository;
    }

    public static NamhocEntityRepository getRepository() {
        return repository;
    }
}
