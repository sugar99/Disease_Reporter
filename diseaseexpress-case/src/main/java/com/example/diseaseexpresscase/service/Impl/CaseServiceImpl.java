package com.example.diseaseexpresscase.service.Impl;


import com.example.diseaseexpresscase.model.Case;
import com.example.diseaseexpresscase.repository.CaseRepository;
import com.example.diseaseexpresscase.service.CaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("caseService")
public class CaseServiceImpl implements CaseService {

    @Resource
    CaseRepository caseRepository;
    
    @Override
    public Case addCase(Case newCase) {
        Case caseAdded = caseRepository.save(newCase);
        return caseAdded;
    }

    @Override
    public Case findCaseById(Integer caseId) {
        Optional<Case> optional = caseRepository.findById(caseId);
        Case userFound = optional.isPresent() ? optional.get() : new Case();
        return userFound;
    }

    @Override
    public List<Case> findCaseByInfo(Case caseInfo) {
        List<Case> list;
        list = caseRepository.findByInfo(caseInfo.getDiseaseId(),caseInfo.getDate(),caseInfo.getPlace(),
                caseInfo.getPatientId(),caseInfo.getUserId());

        return list;
    }

    @Override
    public boolean updateCase(Case newCase) {
        Case old = caseRepository.findById(newCase.getCaseId()).get();

        if(newCase.getDiseaseId()!=null && !"".equals(newCase.getDiseaseId())){
            old.setDiseaseId(newCase.getDiseaseId());
        }
        if(newCase.getDate()!=null && !"".equals(newCase.getDate())){
            old.setDate(newCase.getDate());
        }
        if(newCase.getPlace()!=null && !"".equals(newCase.getPlace())){
            old.setPlace(newCase.getPlace());
        }
        if(newCase.getPatientId()!=null && !"".equals(newCase.getPatientId())){
            old.setPatientId(newCase.getPatientId());
        }
        if(newCase.getUserId()!=null && !"".equals(newCase.getUserId())){
            old.setUserId(newCase.getUserId());
        }

        caseRepository.save(old);
        return true;
    }

    @Override
    public Case deleteCase(Integer caseId) {
        Optional<Case> optional = caseRepository.findById(caseId);
        if(optional.isPresent()){
            caseRepository.deleteById(caseId);
            return optional.get();
        }

        return new Case();
    }
}
