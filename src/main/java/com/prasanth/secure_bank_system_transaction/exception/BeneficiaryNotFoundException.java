package com.prasanth.secure_bank_system_transaction.exception;

public class BeneficiaryNotFoundException extends RuntimeException{
    public BeneficiaryNotFoundException(String message){
        super(message);
    }
}
