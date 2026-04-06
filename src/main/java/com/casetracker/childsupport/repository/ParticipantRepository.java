package com.casetracker.childsupport.repository;

import com.casetracker.childsupport.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findBySupportCaseId(Long caseId);
}