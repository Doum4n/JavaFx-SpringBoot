package com.example.alpha.JavaFx.model;

import com.example.alpha.Spring_boot.result.student.KqSinhVienMonhocEntity;
import com.example.alpha.repository.KqSVMonhocEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class KqMonHoc_SV {
    @Getter
    private static KqSVMonhocEntityRepository repository;

    public KqMonHoc_SV(KqSVMonhocEntityRepository repository){
        KqMonHoc_SV.repository = repository;
    }
}
