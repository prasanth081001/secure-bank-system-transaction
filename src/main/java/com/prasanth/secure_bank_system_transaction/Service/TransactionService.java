package com.prasanth.secure_bank_system_transaction.Service;

import com.prasanth.secure_bank_system_transaction.DTO.TransferRequest;
import com.prasanth.secure_bank_system_transaction.Entity.Account;
import com.prasanth.secure_bank_system_transaction.Entity.TransactionHistory;
import com.prasanth.secure_bank_system_transaction.Repository.AccountRepository;
import com.prasanth.secure_bank_system_transaction.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final RiskAnalyzerService riskAnalyzerService;


    @Transactional
    public String transferMoney(TransferRequest request) {

        LocalDateTime startOfDay =
                LocalDateTime.now().toLocalDate().atStartOfDay();

        LocalDateTime endOfDay =
                startOfDay.plusDays(1);

        long todayTransactions =
                transactionRepository
                        .countByFromAccountAndTransactionTimeBetween(
                                request.getFromAccount(),
                                startOfDay,
                                endOfDay);

        if (todayTransactions >= 5) {
            throw new RuntimeException(
                    "Daily transfer limit exceeded");
        }
        String riskLevel=riskAnalyzerService.checkRisk(request.getAmount());

        Account sender = accountRepository
                .lockAccount(request.getFromAccount())
                .orElseThrow(() ->
                        new RuntimeException("Sender account not found"));
        if ("High".equals(riskLevel)){
            sender.setFrozen(true);
            accountRepository.save(sender);
        }
        if (sender.isFrozen()){
            throw new RuntimeException("Account is frozen. Transfer blocked");
        }

        Account receiver = accountRepository
                .lockAccount(request.getToAccount())
                .orElseThrow(() ->
                        new RuntimeException("Receiver account not found"));

        if(sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(
                sender.getBalance().subtract(request.getAmount()));

        receiver.setBalance(
                receiver.getBalance().add(request.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        TransactionHistory history =
                TransactionHistory.builder()
                        .fromAccount(request.getFromAccount())
                        .toAccount(request.getToAccount())
                        .amount(request.getAmount())
                        .status("SUCCESS")
                        .riskLevel(riskLevel)
                        .transactionTime(LocalDateTime.now())
                        .build();

        transactionRepository.save(history);

        return "Transfer Successful";
    }

}
