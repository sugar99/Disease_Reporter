package com.yonyou.diseasereporter.service;


import com.yonyou.diseasereporter.model.Patient;

import java.util.List;

public interface PatientService {
    public Patient addPatient(Patient newPatient);

    public Patient findPatientById(Integer patientId);

    public List<Patient> findPatientByInfo(Patient patientInfo);

    public boolean updatePatient(Patient patient);

    public Patient deletePatient(Integer patientId);
}
