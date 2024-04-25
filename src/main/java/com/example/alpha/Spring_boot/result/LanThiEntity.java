package com.example.alpha.Spring_boot.result;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@NoArgsConstructor
@Table(name = "LanThi")
@Getter
@Setter
@ToString
public class LanThiEntity {
    @Id
    @Column(name = "LanThi")
    private int LanThi;
}
