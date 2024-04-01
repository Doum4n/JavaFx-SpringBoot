package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.NguoidungEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class TaiKhoan {
    @Getter
    private static NguoidungEntityRepository repository;

    public TaiKhoan(NguoidungEntityRepository repository) {
        TaiKhoan.repository = repository;
    }
}
