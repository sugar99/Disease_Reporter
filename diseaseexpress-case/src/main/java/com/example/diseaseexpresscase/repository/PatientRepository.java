package com.example.diseaseexpresscase.repository;

import com.example.diseaseexpresscase.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * P3.查询患者（info）
     *
     * @param pname
     * @param sex
     * @param age
     * @param idCardNum
     * @return
     */
    @Query(value = "select * from patient where if(?1 !='',pname like concat('%',?1,'%'),1=1)" +
            "and if(?2 !='',sex=?2,1=1) and if(?3 !='',age=?3,1=1) and if(?4 !='',id_card_num=?4,1=1) ",
            nativeQuery = true)
    List<Patient> findByInfo(String pname, Integer sex, Integer age, String idCardNum);
}
