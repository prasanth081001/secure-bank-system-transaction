package com.prasanth.secure_bank_system_transaction.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BeneficiaryRequest {
    @NotBlank
    private String beneficiaryName;
    @NotBlank
    private String beneficiaryAccount;
    @NotBlank
    private String ownerAccount;
}
