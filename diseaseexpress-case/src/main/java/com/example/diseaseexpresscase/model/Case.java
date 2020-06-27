package com.example.diseaseexpresscase.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "disease_case")
public class Case {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "case_id")
    private Integer caseId;

    @Column(name = "disease_id", nullable = false)
    private Integer diseaseId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "case_date", nullable = false)
    private Date casedate;

    @Column(nullable = false, length = 50)
    private String place;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}
