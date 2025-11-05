package com.example.datn.service;

import com.example.datn.dto.DangKyRequest;
import com.example.datn.entity.NhanVien;
import com.example.datn.entity.VaiTro;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DangKyService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    public String dangKy(DangKyRequest request) {
        // Validate
        if (request.getTaiKhoan() == null || request.getTaiKhoan().trim().isEmpty()) {
            return "Tài khoản không được để trống";
        }

        if (request.getMatKhau() == null || request.getMatKhau().length() < 6) {
            return "Mật khẩu phải có ít nhất 6 ký tự";
        }

        if (!request.getMatKhau().equals(request.getXacNhanMatKhau())) {
            return "Mật khẩu xác nhận không khớp";
        }

        // Kiểm tra tài khoản đã tồn tại
        Optional<NhanVien> existingNV = nhanVienRepository.findByTaiKhoan(request.getTaiKhoan());
        if (existingNV.isPresent()) {
            return "Tài khoản đã tồn tại";
        }

        // Tạo mã nhân viên tự động
        String maNhanVien = generateMaNhanVien();

        // Tìm vai trò USER (mặc định cho đăng ký)
        VaiTro vaiTro = vaiTroRepository.findByMaVaiTro("VT02")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò USER"));

        // Tạo nhân viên mới
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(maNhanVien);
        nhanVien.setTenNhanVien(request.getTenNhanVien());
        nhanVien.setTaiKhoan(request.getTaiKhoan());
        nhanVien.setMatKhau(request.getMatKhau()); // Plain text vì dùng NoOpPasswordEncoder
        nhanVien.setEmail(request.getEmail());
        nhanVien.setSdt(request.getSdt());
        nhanVien.setGioiTinh(request.getGioiTinh());
        nhanVien.setDiaChi(request.getDiaChi());
        nhanVien.setVaiTro(vaiTro);
        nhanVien.setTrangThai(1); // Active

        nhanVienRepository.save(nhanVien);
        return "SUCCESS";
    }

    private String generateMaNhanVien() {
        long count = nhanVienRepository.count();
        return String.format("NV%03d", count + 1);
    }
}