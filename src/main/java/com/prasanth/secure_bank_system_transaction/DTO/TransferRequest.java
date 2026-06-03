package com.prasanth.secure_bank_system_transaction.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotBlank
    private String fromAccount;
    @NotBlank
    private String toAccount;
    @Positive
    private BigDecimal amount;
}
