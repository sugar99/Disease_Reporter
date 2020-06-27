package com.yonyou.diseasereporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    @JsonProperty(value = "user_id", required = false)
    private Integer userId;

    @Column(nullable = false, length = 30)
    @JsonProperty(value = "uname")
    private String uname;

    @Column(name = "password", nullable = false, length = 18)
    @JsonProperty(value = "pwd")
    private String pwd;

    @Column(nullable = false, length = 50)
    @JsonProperty(value = "place")
    private String place;

    @Column(nullable = false)
    @JsonProperty(value = "level")
    private Integer level;

}
