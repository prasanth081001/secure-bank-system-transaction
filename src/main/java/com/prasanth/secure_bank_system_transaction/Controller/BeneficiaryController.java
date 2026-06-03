package com.prasanth.secure_bank_system_transaction.Controller;

import com.prasanth.secure_bank_system_transaction.DTO.BeneficiaryRequest;
import com.prasanth.secure_bank_system_transaction.Entity.Beneficiary;
import com.prasanth.secure_bank_system_transaction.Repository.BeneficiaryRepository;
import com.prasanth.secure_bank_system_transaction.Service.BeneficiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;
    private final BeneficiaryRepository beneficiaryRepository;

    @PostMapping
    public String addBeneficiary(
            @Valid @RequestBody BeneficiaryRequest request){

        return beneficiaryService.addBeneficiary(request);
    }

    @GetMapping("/{ownerAccount}")
    public List<Beneficiary> getBeneficiaries(
            @PathVariable String ownerAccount){

        return beneficiaryRepository.findByOwnerAccount(ownerAccount);
    }
}
