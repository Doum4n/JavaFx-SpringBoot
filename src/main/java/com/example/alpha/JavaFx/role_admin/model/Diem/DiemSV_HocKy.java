package com.example.alpha.JavaFx.role_admin.model.Diem;

import com.example.alpha.repository.KqSinnhVienHocKyRepository;
import jakarta.persistence.Column;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DiemSV_HocKy {
    @Getter
    private static KqSinnhVienHocKyRepository repository;
    public DiemSV_HocKy(KqSinnhVienHocKyRepository repository) {
        DiemSV_HocKy.repository = repository;
    }
}
