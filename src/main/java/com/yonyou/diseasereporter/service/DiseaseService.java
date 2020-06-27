package com.yonyou.diseasereporter.service;

import com.yonyou.diseasereporter.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseService {
    public Disease addDisease(Disease newDisease);

    public Disease findDiseaseById(Integer diseaseId);

    public List<Disease> findDiseaseByInfo(Disease diseaseInfo);

    public boolean updateDisease(Disease disease);

    public Disease deleteDisease(Integer diseaseId);
}
