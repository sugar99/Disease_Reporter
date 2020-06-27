package com.example.diseaseexpresscase.controller;

import com.example.diseaseexpresscase.model.Case;
import com.example.diseaseexpresscase.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/case")
@CrossOrigin
public class CaseController {
    @Autowired
    CaseService caseService;

    /**
     * C1.新建病例
     *
     * @return
     */
    @PostMapping(value = "/add")
    public Map<String, Object> addCase(@RequestBody(required = false) Case case1){
        //登录 权限控制
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "添加病例成功");
        resultMap.put("date", caseService.addCase(case1));
        return resultMap;
    }


    /**
     * C2.查询病例（id）
     *
     * @param caseId
     * @return
     */
    @GetMapping("/{case_id}")
    public Map<String, Object> getCaseById(@PathVariable("case_id") Integer caseId){
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "查询病例成功");
        resultMap.put("data", caseService.findCaseById(caseId));
        return resultMap;
    }

    /**
     * C3.查询病例（info）
     *
     * @param diseaseId
     * @param date
     * @param place
     * @param patientId
     * @param userId
     * @return
     */
    @GetMapping("/query")
    public Map<String, Object> getCaseByInfo(@RequestParam(name = "disease_id", required = false) Integer diseaseId,
                                             @RequestParam(name = "date", required = false) Date date,
                                             @RequestParam(name = "place", required = false) String place,
                                             @RequestParam(name = "patient_id", required = false) Integer patientId,
                                             @RequestParam(name = "user_id", required = false) Integer userId){
        Map<String, Object> resultMap = new HashMap<>();

        Case queryCase = new Case();
        queryCase.setDiseaseId(diseaseId);
        queryCase.setDate(date);
        queryCase.setPlace(place);
        queryCase.setPatientId(patientId);
        queryCase.setUserId(userId);

        resultMap.put("status", 200);
        resultMap.put("msg", "查询病例成功");
        resultMap.put("data", caseService.findCaseByInfo(queryCase));
        return resultMap;
    }

    /**
     * C4.更新病例
     *
     * @param newCase
     * @return
     */
    @PostMapping("/update")
    public Map<String, Object> updateCase(@RequestBody(required = false) Case newCase){
        Map<String, Object> resultMap = new HashMap<>();

        if(caseService.updateCase(newCase)){
            resultMap.put("status", 200);
            resultMap.put("msg", "更新病例成功");
            resultMap.put("data", null);
        }

        return resultMap;
    }

    /**
     * C5.删除病例
     *
     * @param caseId
     * @return
     */
    @GetMapping("/delete/{case_id")
    public Map<String, Object> delteCase(@PathVariable(name = "case_id", required = true) Integer caseId){
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "删除病例成功");
        resultMap.put("data", caseService.deleteCase(caseId));
        return resultMap;
    }

}
