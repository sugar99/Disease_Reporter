package com.yonyou.diseasereporter.controller;

import com.yonyou.diseasereporter.model.Disease;
import com.yonyou.diseasereporter.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/disease")
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;

    /**
     * D1.新建疾病
     *
     * @param disease
     * @return
     */
    @PostMapping(value = "/add")
    public Map<String, Object> addDisease(@RequestBody(required = false) Disease disease){
        //登录 权限控制
        Map<String,Object> resultMap = new HashMap<>();

        System.out.println(disease);

        resultMap.put("status", 200);
        resultMap.put("msg", "添加疾病成功");
        resultMap.put("date", diseaseService.addDisease(disease));
        return resultMap;
    }

    /**
     * D2.查询疾病（id）
     *
     * @param diseaseId
     * @return
     */
    @GetMapping("/{disease_id}")
    public Map<String, Object> getDiseaseById(@PathVariable("disease_id") Integer diseaseId){
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "查询疾病成功");
        resultMap.put("data", diseaseService.findDiseaseById(diseaseId));
        return resultMap;
    }

    /**
     * D3.查询疾病（info）
     *
     * @param dname
     * @param nickName
     * @param level
     * @param symptom
     * @param cure
     * @return
     */
    @GetMapping("/query")
    public Map<String,Object> getDiseaseByInfo(@RequestParam(name = "dname", required = false) String dname,
                                               @RequestParam(name = "nick_name", required = false) String nickName,
                                               @RequestParam(name = "level", required = false) Integer level,
                                               @RequestParam(name = "symptom", required = false) String symptom,
                                               @RequestParam(name = "cure", required = false) String cure){
        Map<String,Object> resultMap = new HashMap<>();

        Disease queryDisease = new Disease();
        queryDisease.setDname(dname);
        queryDisease.setNickName(nickName);
        queryDisease.setLevel(level);
        queryDisease.setSymptom(symptom);
        queryDisease.setCure(cure);

        resultMap.put("status", 200);
        resultMap.put("msg", "查询疾病成功");
        resultMap.put("data", diseaseService.findDiseaseByInfo(queryDisease));
        return resultMap;
    }


    /**
     * D4.更新疾病
     *
     * @param newDisease
     * @return
     */
    @PostMapping("/update")
    public Map<String,Object> updateDisease(@RequestBody(required = false) Disease newDisease){
        Map<String,Object> resultMap = new HashMap<>();

        if(diseaseService.updateDisease(newDisease)){
            resultMap.put("status", 200);
            resultMap.put("msg", "更新疾病成功");
            resultMap.put("data", null);
        }

        return resultMap;
    }

    /**
     * D5.删除疾病
     *
     * @param diseaseId
     * @return
     */
    @GetMapping("/delete/{disease_id")
    public Map<String,Object> delteDisease(@PathVariable(name = "disease_id", required = true) Integer diseaseId){
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "删除疾病成功");
        resultMap.put("data", diseaseService.deleteDisease(diseaseId));
        return resultMap;
    }
}
