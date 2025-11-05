package com.example.datn.dto;

import lombok.Data;

@Data
public class DangKyRequest {
    private String tenNhanVien;
    private String taiKhoan;
    private String matKhau;
    private String xacNhanMatKhau;
    private String email;
    private String sdt;
    private String gioiTinh;
    private String diaChi;
}