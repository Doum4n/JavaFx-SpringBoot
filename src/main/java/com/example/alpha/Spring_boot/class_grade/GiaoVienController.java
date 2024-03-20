package com.example.alpha.Spring_boot.class_grade;

import com.example.alpha.Spring_boot.repository.GiaovienEntityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GiaoVienController {
    private final GiaovienEntityRepository giaovienEntityRepository;

    public GiaoVienController(GiaovienEntityRepository giaovienEntityRepository) {
        this.giaovienEntityRepository = giaovienEntityRepository;
    }

    @GetMapping("/GiaoVien/all")
    public List<GiaovienEntity> getGV(){
        return giaovienEntityRepository.findAll();
    }
}
