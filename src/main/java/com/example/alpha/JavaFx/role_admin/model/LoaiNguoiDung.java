package com.example.alpha.JavaFx.role_admin.model;

import com.example.alpha.repository.LoainguoidungEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class LoaiNguoiDung {
    @Getter
    private static LoainguoidungEntityRepository repository;
    public LoaiNguoiDung(LoainguoidungEntityRepository repository) {
        LoaiNguoiDung.repository = repository;
    }
}
