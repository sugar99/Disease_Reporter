package com.example.diseaseexpressmanger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    @JsonProperty(value = "user_id")
    private Integer userId;

    @Column(nullable = false, length = 30)
    private String uname;

    @Column(name = "password", nullable = false, length = 18)
    @JsonProperty(value = "pwd")
    private String pwd;

    @Column(nullable = false, length = 50)
    private String place;

    @Column(nullable = false)
    private Integer level;

}
