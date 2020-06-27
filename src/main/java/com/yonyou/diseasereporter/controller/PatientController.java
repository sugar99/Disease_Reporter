package com.yonyou.diseasereporter.controller;

import com.yonyou.diseasereporter.model.Patient;
import com.yonyou.diseasereporter.service.Impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientServiceImpl patientService;

    /**
     * P1.新建患者
     *
     * @param patient
     * @return
     */
    @PostMapping("/add")
    public Map<String, Object> addPatient(@RequestBody(required = false) Patient patient){
        Map<String,Object> resultMap = new HashMap<>();

        System.out.println(patient);

        resultMap.put("status", 200);
        resultMap.put("msg", "添加患者成功");
        resultMap.put("date", patientService.addPatient(patient));
        return resultMap;
    }

    /**
     * P2.查询患者（id）
     *
     * @param patientId
     * @return
     */
    @GetMapping("/{patient_id}")
    public Map<String, Object> getPatientById(@PathVariable("patient_id") Integer patientId){
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "查询患者成功");
        resultMap.put("data", patientService.findPatientById(patientId));
        return resultMap;
    }

    /**
     * P3.查询患者（info）
     *
     * @param pname
     * @param sex
     * @param age
     * @param idCardNum
     * @return
     */
    @GetMapping("/query")
    public Map<String,Object> getPatientByInfo(@RequestParam(name = "name", required = false) String pname,
                                               @RequestParam(name = "sex", required = false) Integer sex,
                                               @RequestParam(name = "age", required = false) Integer age,
                                               @RequestParam(name = "id_card_num", required = false) String idCardNum){
        Map<String,Object> resultMap = new HashMap<>();

        Patient queryPatient = new Patient();
        queryPatient.setPname(pname);
        queryPatient.setSex(sex);
        queryPatient.setAge(age);
        queryPatient.setIdCardNum(idCardNum);

        resultMap.put("status", 200);
        resultMap.put("msg", "查询患者成功");
        resultMap.put("data", patientService.findPatientByInfo(queryPatient));
        return resultMap;
    }

    /**
     * P4.更新患者
     *
     * @param newPatient
     * @return
     */
    @PostMapping("/update")
    public Map<String,Object> updatePatient(@RequestBody(required = false) Patient newPatient){
        Map<String,Object> resultMap = new HashMap<>();

        if(patientService.updatePatient(newPatient)){
            resultMap.put("status", 200);
            resultMap.put("msg", "更新患者成功");
            resultMap.put("data", null);
        }

        return resultMap;
    }

    /**
     * P5.删除患者
     *
     * @param patientId
     * @return
     */
    @GetMapping("/delete/{patient_id")
    public Map<String,Object> deltePatient(@PathVariable(name = "patient_id", required = true) Integer patientId){
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "删除患者成功");
        resultMap.put("data", patientService.deletePatient(patientId));
        return resultMap;
    }
    
    
}
