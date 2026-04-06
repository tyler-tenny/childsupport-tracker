package com.casetracker.childsupport.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String caseNumber;

    @Column(nullable = false)
    private String status; // OPEN, CLOSED, PENDING

    @Column(nullable = false)
    private String caseType; // ESTABLISHMENT, ENFORCEMENT, CUSTOMER_SERVICE

    private LocalDate openedDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCaseType() { return caseType; }
    public void setCaseType(String caseType) { this.caseType = caseType; }
    public LocalDate getOpenedDate() { return openedDate; }
    public void setOpenedDate(LocalDate openedDate) { this.openedDate = openedDate; }
}