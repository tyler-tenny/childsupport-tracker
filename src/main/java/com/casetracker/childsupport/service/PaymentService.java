package com.casetracker.childsupport.service;

import com.casetracker.childsupport.model.Payment;
import com.casetracker.childsupport.repository.CaseRepository;
import com.casetracker.childsupport.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CaseRepository caseRepository;

    public PaymentService(PaymentRepository paymentRepository, CaseRepository caseRepository) {
        this.paymentRepository = paymentRepository;
        this.caseRepository = caseRepository;
    }

    public List<Payment> getPaymentsForCase(Long caseId) {
        return paymentRepository.findBySupportCaseId(caseId);
    }

    public Payment createPayment(Long caseId, Payment payment) {
        return caseRepository.findById(caseId).map(supportCase -> {
            payment.setSupportCase(supportCase);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Case not found: " + caseId));
    }

    public Payment updatePaymentStatus(Long paymentId, String status) {
        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found: " + paymentId));
    }
}