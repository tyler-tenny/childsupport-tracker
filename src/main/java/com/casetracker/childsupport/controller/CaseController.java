package com.casetracker.childsupport.controller;

import com.casetracker.childsupport.model.Case;
import com.casetracker.childsupport.model.Payment;
import com.casetracker.childsupport.service.CaseService;
import com.casetracker.childsupport.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    private final CaseService caseService;
    private final PaymentService paymentService;

    public CaseController(CaseService caseService, PaymentService paymentService) {
        this.paymentService = paymentService;
        this.caseService = caseService;
    }

    @GetMapping
    public List<Case> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/{id}/payments")
    public List<Payment> getPayments(@PathVariable Long id) {
        return paymentService.getPaymentsForCase(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Case> getCaseById(@PathVariable Long id) {
        return caseService.getCaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Case createCase(@RequestBody Case supportCase) {
        return caseService.createCase(supportCase);
    }


    @PostMapping("/{id}/payments")
    public Payment createPayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentService.createPayment(id, payment);
    }

    @PutMapping("/{id}")
    public Case updateCase(@PathVariable Long id, @RequestBody Case updated) {
        return caseService.updateCase(id, updated);
    }
}