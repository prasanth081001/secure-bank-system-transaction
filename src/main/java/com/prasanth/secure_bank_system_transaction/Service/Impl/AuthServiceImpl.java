package com.prasanth.secure_bank_system_transaction.Service.Impl;

import com.prasanth.secure_bank_system_transaction.DTO.AuthResponse;
import com.prasanth.secure_bank_system_transaction.DTO.LoginRequest;
import com.prasanth.secure_bank_system_transaction.DTO.RegisterRequest;
import com.prasanth.secure_bank_system_transaction.Entity.Role;
import com.prasanth.secure_bank_system_transaction.Entity.User;
import com.prasanth.secure_bank_system_transaction.Repository.UserRepository;
import com.prasanth.secure_bank_system_transaction.Security.JwtService;
import com.prasanth.secure_bank_system_transaction.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user =
                userRepository.findByEmail(
                                request.getEmail())
                        .orElseThrow();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Credentials");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail());

        return new AuthResponse(token);
    }
}
