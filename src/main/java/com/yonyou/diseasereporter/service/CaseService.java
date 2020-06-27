package com.yonyou.diseasereporter.service;

import com.yonyou.diseasereporter.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseService{
    public Case addCase(Case newCase);

    public Case findCaseById(Integer caseId);

    public List<Case> findCaseByInfo(Case caseInfo);

    public boolean updateCase(Case newCase);

    public Case deleteCase(Integer caseId);
}
