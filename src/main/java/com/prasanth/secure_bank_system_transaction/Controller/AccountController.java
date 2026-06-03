package com.prasanth.secure_bank_system_transaction.Controller;

import com.prasanth.secure_bank_system_transaction.DTO.AccountRequest;
import com.prasanth.secure_bank_system_transaction.DTO.DepositRequest;
import com.prasanth.secure_bank_system_transaction.DTO.TransferRequest;
import com.prasanth.secure_bank_system_transaction.DTO.WithdrawRequest;
import com.prasanth.secure_bank_system_transaction.Entity.Account;
import com.prasanth.secure_bank_system_transaction.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/test")
    public String testApi(){
        return "JWT Authentication success";
    }
    @PostMapping("/create")
    public Account createAccount(@Valid@RequestBody AccountRequest request){
        return accountService.createAccount(request);
    }
    @PostMapping("/deposit")
    public Account deposit(@Valid @RequestBody DepositRequest request){
        return accountService.deposit(request);
    }
    @PostMapping("/withdraw")
    public Account withdraw(@Valid @RequestBody WithdrawRequest request){
        return accountService.withdraw(request);
    }
    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody TransferRequest request){
        return accountService.transfer(request);
    }
    @PutMapping("/unfreeze/{accountNumber}")
    public String unfreezeAccount(@PathVariable String accountNumber){
        return accountService.unfreezeAccount(accountNumber);
    }
}
