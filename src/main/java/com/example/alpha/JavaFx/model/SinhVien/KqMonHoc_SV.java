package com.example.alpha.JavaFx.model.SinhVien;

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
