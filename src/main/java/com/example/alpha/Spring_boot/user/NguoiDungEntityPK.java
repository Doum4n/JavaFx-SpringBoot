package com.example.alpha.Spring_boot.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class NguoiDungEntityPK implements Serializable {
    private String maLoai;
    private String tenDangNhap;
}
