package com.prasanth.secure_bank_system_transaction.Controller;

import com.prasanth.secure_bank_system_transaction.DTO.AuthResponse;
import com.prasanth.secure_bank_system_transaction.DTO.LoginRequest;
import com.prasanth.secure_bank_system_transaction.DTO.RegisterRequest;
import com.prasanth.secure_bank_system_transaction.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
