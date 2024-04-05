package com.example.alpha.Spring_boot.subject;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiemQTPK implements Serializable {
    private String MaSV;
    private String MaMH;
    private String MaHK;
    protected String MaNH;
}
