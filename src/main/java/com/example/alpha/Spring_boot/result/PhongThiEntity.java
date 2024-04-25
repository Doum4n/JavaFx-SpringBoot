package com.example.alpha.Spring_boot.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PhongThi")
public class PhongThiEntity {
    @Id
    private String maPhongThi; //ma lop
}
