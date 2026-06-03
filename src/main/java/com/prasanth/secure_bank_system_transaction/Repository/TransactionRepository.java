package com.prasanth.secure_bank_system_transaction.Repository;

import com.prasanth.secure_bank_system_transaction.Entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionHistory,Long> {
    List<TransactionHistory> findByFromAccountOrToAccount(String fromAccount,
                                                          String toAccount);
    List<TransactionHistory> findByRiskLevel(String riskLevel);
    Long countByFromAccountAndTransactionTimeBetween(
            String fromAccount, LocalDateTime start,LocalDateTime end
            );
}
