package com.example.diseaseexpressmanger.service;


import com.example.diseaseexpressmanger.model.Disease;

import java.util.List;

public interface DiseaseService {
    public Disease addDisease(Disease newDisease);

    public Disease findDiseaseById(Integer diseaseId);

    public List<Disease> findDiseaseByInfo(Disease diseaseInfo);

    public boolean updateDisease(Disease disease);

    public Disease deleteDisease(Integer diseaseId);
}
