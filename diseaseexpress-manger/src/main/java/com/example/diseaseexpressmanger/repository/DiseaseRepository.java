package com.example.diseaseexpressmanger.repository;

import com.example.diseaseexpressmanger.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    @Query(value = "select * from disease where if(?1 !='',dname like concat('%',?1,'%'),1=1)" +
            "and if(?2 !='',nick_name like concat('%',?2,'%',1=1) and if(?3 !='',level=?3,1=1)" +
            "and if(?4 !='',symptom like concat('%',?4,'%',1=1) and if(?5 !='',cure like concat('%',?5,'%'),1=1)",
            nativeQuery = true)
    List<Disease> findByInfo(String dname, String nickName, Integer level, String symptom, String cure);
}
