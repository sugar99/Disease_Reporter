package com.example.diseaseexpressmanger.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "disease")
public class Disease {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "disease_id")
    private Integer diseaseId;

    @Column(nullable = false, length = 30)
    private String dname;

    @Column(name = "nick_name", length = 20)
    private String nickName;

    @Column(nullable = false)
    private Integer level;

    @Column(length = 500)
    private String symptom;

    @Column(length = 500)
    private String cure;
}
