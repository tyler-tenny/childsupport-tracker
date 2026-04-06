package com.casetracker.childsupport.repository;

import com.casetracker.childsupport.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Long> {
    List<Case> findByStatus(String status);
    List<Case> findByCaseType(String caseType);
}