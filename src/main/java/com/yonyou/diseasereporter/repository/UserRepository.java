package com.yonyou.diseasereporter.repository;

import com.yonyou.diseasereporter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



    /**
     * U3.查询用户（info）
     *
     * @param uname
     * @param place
     * @param level
     * @return
     */
    @Query(value = "select * from user where if(?1 !='',uname like concat('%',?1,'%'),1=1)" +
            "and if(?2 !='',place like concat('%',?2,'%'),1=1) and if(?3 !='',user.level=?3,1=1)"
            ,nativeQuery = true)
    List<User> findByInfo(String uname, String place, Integer level);



}
