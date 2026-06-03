package com.prasanth.secure_bank_system_transaction.Service;

import com.prasanth.secure_bank_system_transaction.DTO.BeneficiaryRequest;
import com.prasanth.secure_bank_system_transaction.Entity.Beneficiary;
import com.prasanth.secure_bank_system_transaction.Repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;

    public String addBeneficiary(
            BeneficiaryRequest request){

        Beneficiary beneficiary =
                Beneficiary.builder()
                        .beneficiaryName(request.getBeneficiaryName())
                        .beneficiaryAccount(request.getBeneficiaryAccount())
                        .ownerAccount(request.getOwnerAccount())
                        .build();

        beneficiaryRepository.save(beneficiary);

        return "Beneficiary Added Successfully";
    }
}
