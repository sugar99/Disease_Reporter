package com.yonyou.diseasereporter.service.Impl;

import com.yonyou.diseasereporter.model.Patient;
import com.yonyou.diseasereporter.repository.PatientRepository;
import com.yonyou.diseasereporter.service.PatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service("patientService")
public class PatientServiceImpl implements PatientService {
    @Resource
    PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient newPatient) {
        Patient patientAdded = patientRepository.save(newPatient);
        return patientAdded;
    }

    @Override
    public Patient findPatientById(Integer patientId) {
        Optional<Patient> optional = patientRepository.findById(patientId);
        Patient patient = (optional.isPresent()) ? optional.get() : new Patient();
        return patient;
    }

    @Override
    public List<Patient> findPatientByInfo(Patient patientInfo) {
        List<Patient> list = patientRepository.findByInfo(patientInfo.getPname(),patientInfo.getSex(),
                patientInfo.getAge(),patientInfo.getIdCardNum());
        return list;
    }

    @Override
    public boolean updatePatient(Patient newPatient) {
        Patient old = patientRepository.findById(newPatient.getPatientId()).get();
//        String pname, Integer sex, Integer age, String idCardNum
        if(newPatient.getPname()!=null && !"".equals(newPatient.getPname())){
            old.setPname(newPatient.getPname());
        }
        if(newPatient.getSex()!=null && !"".equals(newPatient.getSex())){
            old.setSex(newPatient.getSex());
        }
        if(newPatient.getAge()!=null && !"".equals(newPatient.getAge())){
            old.setAge(newPatient.getAge());
        }
        if(newPatient.getIdCardNum()!=null && !"".equals(newPatient.getIdCardNum())){
            old.setIdCardNum(newPatient.getIdCardNum());
        }
        patientRepository.save(old);
        return true;
    }

    @Override
    public Patient deletePatient(Integer patientId) {
        Optional<Patient> optional = patientRepository.findById(patientId);
        if(optional.isPresent()){
            patientRepository.deleteById(patientId);
            return optional.get();
        }

        return new Patient();
    }
}
