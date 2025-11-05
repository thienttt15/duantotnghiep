package com.example.datn.controller;

import com.example.datn.dto.DangKyRequest;
import com.example.datn.service.DangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DangKyController {

    @Autowired
    private DangKyService dangKyService;

    @GetMapping("/dangky")
    public String showDangKyForm(Model model) {
        model.addAttribute("dangKyRequest", new DangKyRequest());
        return "dangky";
    }

    @PostMapping("/dangky")
    public String dangKy(@ModelAttribute DangKyRequest request, RedirectAttributes redirectAttributes) {
        String result = dangKyService.dangKy(request);

        if ("SUCCESS".equals(result)) {
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", result);
            return "redirect:/dangky";
        }
    }
}