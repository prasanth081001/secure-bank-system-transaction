package com.prasanth.secure_bank_system_transaction.Repository;

import com.prasanth.secure_bank_system_transaction.Entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
