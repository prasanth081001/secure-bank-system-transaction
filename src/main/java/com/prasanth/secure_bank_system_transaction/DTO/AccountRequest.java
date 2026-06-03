package com.prasanth.secure_bank_system_transaction.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {
    @NotBlank
    private String accountHolderName;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @PositiveOrZero
    private BigDecimal balance;
}
