package com.example.datn.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "KhachHang")
@Data
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_KhachHang")
    private Integer id;

    @Column(name = "MaKhachHang")
    private String maKhachHang;

    @Column(name = "TenKhachHang")
    private String tenKhachHang;

    @Column(name = "Email")
    private String email;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "NgayTao")
    private LocalDate ngayTao;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "TrangThai")
    private Boolean trangThai;
}