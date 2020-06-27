package com.example.diseaseexpresscase.service;




import com.example.diseaseexpresscase.model.Patient;
import java.util.List;


public interface PatientService {
    public Patient addPatient(Patient newPatient);

    public Patient findPatientById(Integer patientId);

    public List<Patient> findPatientByInfo(Patient patientInfo);

    public boolean updatePatient(Patient patient);

    public Patient deletePatient(Integer patientId);
}
