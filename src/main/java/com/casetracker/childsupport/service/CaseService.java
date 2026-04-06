package com.casetracker.childsupport.service;

import com.casetracker.childsupport.model.Case;
import com.casetracker.childsupport.repository.CaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public Optional<Case> getCaseById(Long id) {
        return caseRepository.findById(id);
    }

    public List<Case> getCasesByStatus(String status) {
        return caseRepository.findByStatus(status);
    }

    public Case createCase(Case supportCase) {
        return caseRepository.save(supportCase);
    }

    public Case updateCase(Long id, Case updated) {
        return caseRepository.findById(id).map(existing -> {
            existing.setStatus(updated.getStatus());
            existing.setCaseType(updated.getCaseType());
            existing.setOpenedDate(updated.getOpenedDate());
            return caseRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Case not found: " + id));
    }

    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}