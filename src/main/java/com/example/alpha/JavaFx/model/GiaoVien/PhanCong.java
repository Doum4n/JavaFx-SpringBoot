package com.example.alpha.JavaFx.model.GiaoVien;

import com.example.alpha.repository.PhancongEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class PhanCong {
    @Getter
    private static PhancongEntityRepository repository = null;

    public PhanCong(PhancongEntityRepository repository){
        PhanCong.repository=repository;
    }

}
