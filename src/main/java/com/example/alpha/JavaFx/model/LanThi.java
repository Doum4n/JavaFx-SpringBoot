package com.example.alpha.JavaFx.model;

import com.example.alpha.Spring_boot.result.LanThiEntity;
import com.example.alpha.repository.LanThiEntityRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class LanThi {
    @Getter
    private static LanThiEntityRepository repository;
    public LanThi(LanThiEntityRepository repository){
        LanThi.repository = repository;
    }
}
