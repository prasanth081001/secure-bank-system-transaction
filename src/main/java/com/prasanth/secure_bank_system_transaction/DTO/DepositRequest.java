package com.prasanth.secure_bank_system_transaction.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    @NotBlank
    private String accountNumber;
    @Positive
    private BigDecimal amount;
}
