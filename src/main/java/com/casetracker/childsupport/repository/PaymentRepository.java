package com.casetracker.childsupport.repository;

import com.casetracker.childsupport.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findBySupportCaseId(Long caseId);
    List<Payment> findByStatus(String status);
}