package com.yonyou.diseasereporter.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patient")
public class Patient {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(nullable = false, length = 30)
    private String pname;

    @Column(nullable = false)
    private Integer sex;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "id_card_num", nullable = true, length = 20)
    private String idCardNum;
}
