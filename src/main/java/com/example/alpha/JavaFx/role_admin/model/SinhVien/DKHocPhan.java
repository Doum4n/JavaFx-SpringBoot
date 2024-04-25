package com.example.alpha.JavaFx.role_admin.model.SinhVien;

import com.example.alpha.repository.DKHocPhanEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DKHocPhan {
    @Getter
    private static DKHocPhanEntityRepository repository;
    public DKHocPhan(DKHocPhanEntityRepository repository) {
        DKHocPhan.repository = repository;
    }
}
