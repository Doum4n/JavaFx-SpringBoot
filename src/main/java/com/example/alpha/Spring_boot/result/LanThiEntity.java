package com.example.alpha.Spring_boot.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LanThi")
public class LanThiEntity {
    @Id
    @Column(name = "LanThi")
    private int LanThi;
}
