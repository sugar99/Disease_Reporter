package com.yonyou.diseasereporter.repository;

import com.yonyou.diseasereporter.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {
    @Query(value = "select * from case where if(?1 !='',diseaseId=?1,1=1) and if(?2 !='',date=?2,1=1)" +
            "and if(?3 !='',place concat('%','?3','%'),1=1) and if(?4 !='',patientId=?4,1=1) and if(?5 != '',user_id=?5,1=1)",
            nativeQuery = true)
    List<Case> findByInfo(Integer diseaseId, Date date, String place, Integer patientId, Integer userId);
}
