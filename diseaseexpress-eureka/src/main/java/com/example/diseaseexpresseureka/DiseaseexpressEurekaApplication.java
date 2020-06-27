package com.example.diseaseexpresseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiseaseexpressEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiseaseexpressEurekaApplication.class, args);
    }

}
