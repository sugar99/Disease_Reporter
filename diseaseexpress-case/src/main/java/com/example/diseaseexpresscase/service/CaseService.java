package com.example.diseaseexpresscase.service;



import com.example.diseaseexpresscase.model.Case;

import java.util.List;
import java.util.Map;

public interface CaseService{
    public Case addCase(Case newCase);

    public Case findCaseById(Integer caseId);

    public List<Case> findCaseByInfo(Case caseInfo);

    public boolean updateCase(Case newCase);

    public Case deleteCase(Integer caseId);
}
