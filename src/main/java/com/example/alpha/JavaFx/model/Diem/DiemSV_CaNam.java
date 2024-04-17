package com.example.alpha.JavaFx.model.Diem;

import com.example.alpha.Spring_boot.result.student.KqSinhVienCanamEntity;
import com.example.alpha.repository.KqSVCanamEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DiemSV_CaNam {
    @Getter
    private static KqSVCanamEntityRepository repository;
    public DiemSV_CaNam(KqSVCanamEntityRepository repository) {
        DiemSV_CaNam.repository = repository;
    }
}
