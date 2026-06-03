package com.prasanth.secure_bank_system_transaction.Service;

import com.prasanth.secure_bank_system_transaction.DTO.AuthResponse;
import com.prasanth.secure_bank_system_transaction.DTO.LoginRequest;
import com.prasanth.secure_bank_system_transaction.DTO.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
