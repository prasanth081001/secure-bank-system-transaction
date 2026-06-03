package com.prasanth.secure_bank_system_transaction.Service;

import com.prasanth.secure_bank_system_transaction.DTO.AccountRequest;
import com.prasanth.secure_bank_system_transaction.DTO.DepositRequest;
import com.prasanth.secure_bank_system_transaction.DTO.TransferRequest;
import com.prasanth.secure_bank_system_transaction.DTO.WithdrawRequest;
import com.prasanth.secure_bank_system_transaction.Entity.Account;
import com.prasanth.secure_bank_system_transaction.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(AccountRequest request) {

        String accountNumber =
                "ACC" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountHolderName(request.getAccountHolderName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .balance(request.getBalance())
                .status("ACTIVE")
                .build();

        return accountRepository.save(account);
    }
    public Account deposit(DepositRequest request) {

        Account account = accountRepository
                .findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        account.setBalance(
                account.getBalance().add(request.getAmount()));

        return accountRepository.save(account);
    }
    public Account withdraw(WithdrawRequest request) {

        Account account = accountRepository
                .findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }

        account.setBalance(
                account.getBalance().subtract(request.getAmount()));

        return accountRepository.save(account);
    }
    @Transactional
    public String transfer(TransferRequest request) {

        Account sender = accountRepository
                .lockAccount(request.getFromAccount())
                .orElseThrow(() ->
                        new RuntimeException("Sender account not found"));

        Account receiver = accountRepository
                .lockAccount(request.getToAccount())
                .orElseThrow(() ->
                        new RuntimeException("Receiver account not found"));

        if (sender.getBalance()
                .compareTo(request.getAmount()) < 0) {

            throw new RuntimeException("Insufficient Balance");
        }

        sender.setBalance(
                sender.getBalance()
                        .subtract(request.getAmount()));

        receiver.setBalance(
                receiver.getBalance()
                        .add(request.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        return "Transfer Successful";
    }
    public String unfreezeAccount(String accountNumber) {

        Account account =
                accountRepository.findByAccountNumber(accountNumber)
                        .orElseThrow(() ->
                                new RuntimeException("Account not found"));

        account.setFrozen(false);

        accountRepository.save(account);

        return "Account Unfrozen Successfully";
    }
}
