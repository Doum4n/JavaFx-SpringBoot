package com.example.alpha.Spring_boot.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PhongThiEntity {
    @Id
    private String maPhongThi; //ma lop
}
