package com.prasanth.secure_bank_system_transaction.Service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RiskAnalyzerService {
    public String checkRisk(BigDecimal amount) {

        if (amount.compareTo(new BigDecimal("100000")) > 0) {
            return "HIGH";
        }

        if (amount.compareTo(new BigDecimal("50000")) > 0) {
            return "MEDIUM";
        }

        return "LOW";
    }
}
