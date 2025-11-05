package com.example.datn.security;

import com.example.datn.entity.NhanVien;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final NhanVien nhanVien;

    public CustomUserDetails(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (nhanVien.getVaiTro() == null) {
            System.out.println("❌ VAI TRÒ NULL!");
            return List.of();
        }

        String roleName = nhanVien.getVaiTro().getTenVaiTro();
        String authority = "ROLE_" + roleName;
        System.out.println("✅ AUTHORITY: " + authority);

        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        String password = nhanVien.getMatKhau();
        System.out.println("   - Password trả về: " + password);
        return password;
    }

    @Override
    public String getUsername() {
        return nhanVien.getTaiKhoan();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Sửa lỗi: So sánh Integer với Integer, không phải boolean với Integer
        boolean enabled = nhanVien.getTrangThai() != null && nhanVien.getTrangThai() == 1;
        System.out.println("   - isEnabled: " + enabled);
        return enabled;
    }
}