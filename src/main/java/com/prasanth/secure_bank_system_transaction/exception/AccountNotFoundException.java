package com.prasanth.secure_bank_system_transaction.exception;

public class AccountNotFoundException  extends RuntimeException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
