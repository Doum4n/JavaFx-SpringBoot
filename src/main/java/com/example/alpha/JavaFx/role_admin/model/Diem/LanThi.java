package com.example.alpha.JavaFx.role_admin.model.Diem;

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
