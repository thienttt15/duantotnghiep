package com.example.datn.security;

import com.example.datn.entity.NhanVien;
import com.example.datn.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== BẮT ĐẦU TÌM USER: " + username + " ===");

        NhanVien nhanVien = nhanVienRepository.findByTaiKhoan(username)
                .orElseThrow(() -> {
                    System.out.println("❌ KHÔNG TÌM THẤY USER: " + username);
                    return new UsernameNotFoundException("Không tìm thấy user: " + username);
                });

        System.out.println("✅ ĐÃ TÌM THẤY USER:");
        System.out.println("   - ID: " + nhanVien.getId());
        System.out.println("   - TaiKhoan: " + nhanVien.getTaiKhoan());
        System.out.println("   - MatKhau: " + nhanVien.getMatKhau());
        System.out.println("   - TrangThai: " + nhanVien.getTrangThai());

        if (nhanVien.getVaiTro() != null) {
            System.out.println("   - VaiTro: " + nhanVien.getVaiTro().getTenVaiTro());
            System.out.println("   - ID_VaiTro: " + nhanVien.getVaiTro().getIdVaiTro());
        } else {
            System.out.println("   - ⚠️ VAI TRÒ LÀ NULL!");
        }

        return new CustomUserDetails(nhanVien);
    }
}