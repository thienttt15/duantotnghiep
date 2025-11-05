//package com.example.datn.controller;
//
//import com.example.datn.entity.NhanVien;
//import com.example.datn.repository.NhanVienRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class TestController {
//
//    @Autowired
//    private NhanVienRepository nhanVienRepository;
//
//    @GetMapping("/test/nhanvien")
//    public List<NhanVien> testNhanVien() {
//        return nhanVienRepository.findAll();
//    }
//
//    @GetMapping("/test/admin")
//    public NhanVien testAdmin() {
//        return nhanVienRepository.findByTaiKhoan("admin")
//                .orElse(null);
//    }
//}