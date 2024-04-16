package com.example.alpha.JavaFx.model.Diem;

import com.example.alpha.Spring_boot.result.DiemRLSinhVien;
import com.example.alpha.repository.DiemRLSinhVienRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DiemRenLuyen {
    @Getter
    private static DiemRLSinhVienRepository repository;
    public DiemRenLuyen(DiemRLSinhVienRepository repository) {
        DiemRenLuyen.repository = repository;
    }
}
