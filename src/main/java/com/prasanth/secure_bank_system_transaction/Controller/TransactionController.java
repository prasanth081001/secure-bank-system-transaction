package com.prasanth.secure_bank_system_transaction.Controller;

import com.prasanth.secure_bank_system_transaction.DTO.TransferRequest;
import com.prasanth.secure_bank_system_transaction.Entity.TransactionHistory;
import com.prasanth.secure_bank_system_transaction.Repository.TransactionRepository;
import com.prasanth.secure_bank_system_transaction.Service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    @PostMapping("/transfer")
    public String transferMoney(
            @Valid @RequestBody TransferRequest request) {

        return transactionService.transferMoney(request);
    }

    @GetMapping("/history/{accountNumber}")
    public List<TransactionHistory> getHistory(
            @PathVariable String accountNumber) {

        return transactionRepository
                .findByFromAccountOrToAccount(
                        accountNumber,
                        accountNumber);
    }
    @GetMapping("/high-risk")
    public List<TransactionHistory> highRiskTransactions(){
        return transactionRepository.findByRiskLevel("HIGH");
    }

    @GetMapping("/fraud-check")
    public List<TransactionHistory> fraudCheck(){
        return transactionRepository.findByRiskLevel("High");
    }
}
