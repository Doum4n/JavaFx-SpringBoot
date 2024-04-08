package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.MonhocEntityRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class MonHoc {
    @Getter
    private static MonhocEntityRepository repository;

    public MonHoc(MonhocEntityRepository repository){
        MonHoc.repository = repository;
    }
}
