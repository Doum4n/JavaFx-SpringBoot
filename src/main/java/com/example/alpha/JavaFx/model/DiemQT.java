package com.example.alpha.JavaFx.model;

import com.example.alpha.repository.DiemQTEntityRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DiemQT {
    @Getter
    private static DiemQTEntityRepository repository;
    public DiemQT(DiemQTEntityRepository repository){
        DiemQT.repository = repository;
    }
}
