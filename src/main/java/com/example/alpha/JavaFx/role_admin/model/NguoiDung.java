package com.example.alpha.JavaFx.role_admin.model;

import com.example.alpha.repository.NguoidungEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class NguoiDung {
    @Getter
    private static NguoidungEntityRepository repository;
    public NguoiDung(NguoidungEntityRepository repository) {
        NguoiDung.repository = repository;
    }
}
