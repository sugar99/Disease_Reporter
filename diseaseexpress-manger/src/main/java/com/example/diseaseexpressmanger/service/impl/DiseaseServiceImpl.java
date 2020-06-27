package com.example.diseaseexpressmanger.service.impl;

import com.example.diseaseexpressmanger.model.Disease;
import com.example.diseaseexpressmanger.repository.DiseaseRepository;
import com.example.diseaseexpressmanger.service.DiseaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service("diseaseService")
public class DiseaseServiceImpl implements DiseaseService {
    @Resource
    DiseaseRepository diseaseRepository;

    @Override
    public Disease addDisease(Disease newDisease) {
        Disease diseaseAdded = diseaseRepository.save(newDisease);
        return diseaseAdded;
    }

    @Override
    public Disease findDiseaseById(Integer diseaseId) {
        Optional<Disease> optional = diseaseRepository.findById(diseaseId);
        Disease disease = optional.isPresent() ? optional.get() : new Disease();
        return disease;
    }

    @Override
    public List<Disease> findDiseaseByInfo(Disease diseaseInfo) {
//        String dname, String nickName, Integer level, String symptom, String cure
        List<Disease> list = diseaseRepository.findByInfo(diseaseInfo.getDname(),diseaseInfo.getNickName(),
                diseaseInfo.getLevel(),diseaseInfo.getSymptom(),diseaseInfo.getCure());
        return list;
    }

    @Override
    public boolean updateDisease(Disease newDisease) {
        Disease old = diseaseRepository.findById(newDisease.getDiseaseId()).get();
//        String dname, String nickName, Integer level, String symptom, String cure
        if(newDisease.getDname()!=null && !"".equals(newDisease.getDname())){
            old.setDname(newDisease.getDname());
        }
        if(newDisease.getNickName()!=null && !"".equals(newDisease.getNickName())){
            old.setNickName(newDisease.getNickName());
        }
        if(newDisease.getLevel()!=null && !"".equals(newDisease.getLevel())){
            old.setLevel(newDisease.getLevel());
        }
        if(newDisease.getSymptom()!=null && !"".equals(newDisease.getSymptom())){
            old.setSymptom(newDisease.getSymptom());
        }
        if(newDisease.getCure()!=null && !"".equals(newDisease.getCure())){
            old.setCure(newDisease.getCure());
        }
        return false;
    }

    @Override
    public Disease deleteDisease(Integer diseaseId) {
        Optional<Disease> optional = diseaseRepository.findById(diseaseId);
        if(optional.isPresent()){
            diseaseRepository.deleteById(diseaseId);
            return optional.get();
        }
        return new Disease();
    }
}
