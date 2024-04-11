package com.example.alpha.JavaFx.model.MonHoc;

import com.example.alpha.repository.MonhocEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class MonHoc {
    @Getter
    private static MonhocEntityRepository repository;

    public MonHoc(MonhocEntityRepository repository){
        MonHoc.repository = repository;
    }
}
