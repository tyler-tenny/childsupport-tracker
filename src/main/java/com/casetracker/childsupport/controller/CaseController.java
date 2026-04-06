package com.casetracker.childsupport.controller;

import com.casetracker.childsupport.model.Case;
import com.casetracker.childsupport.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping
    public List<Case> getAllCases() {
        return caseService.getAllCases();
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

    @PutMapping("/{id}")
    public Case updateCase(@PathVariable Long id, @RequestBody Case updated) {
        return caseService.updateCase(id, updated);
    }
}