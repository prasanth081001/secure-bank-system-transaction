package com.prasanth.secure_bank_system_transaction.Repository;

import com.prasanth.secure_bank_system_transaction.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
    List<Beneficiary> findByOwnerAccount(String ownerAccount);
}
