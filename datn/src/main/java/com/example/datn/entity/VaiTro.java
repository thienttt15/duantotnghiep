package com.example.datn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VaiTro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaiTro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VaiTro")
    private Integer idVaiTro;

    @Column(name = "MaVaiTro")
    private String maVaiTro;

    @Column(name = "TenVaiTro")
    private String tenVaiTro;

    @Column(name = "TrangThai")
    private Boolean trangThai;
}