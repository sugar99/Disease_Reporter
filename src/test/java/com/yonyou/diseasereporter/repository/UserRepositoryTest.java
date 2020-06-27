package com.yonyou.diseasereporter.repository;

import com.yonyou.diseasereporter.model.User;
import jdk.nashorn.internal.ir.annotations.Ignore;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {
    @Resource
    UserRepository userRepository;

    @Test
    void findByInfo() {
        User u1 = new User();
        u1.setUname("医院");
        List<User> list = userRepository.findByInfo(u1.getUname(),u1.getPlace(),u1.getLevel());

        System.out.println("list size:" + list.size());
        System.out.println(list);
    }

    @Test
    @Ignore
    void addUser(){
        User user = new User();
        user.setUname("TestUname");
        user.setPwd("TestPwd");
        user.setLevel(1);
        user.setPlace("TestPlace");

        userRepository.save(user);
    }

    @Test
    @Ignore
    void findUserById(){
        int id = 2;

        Optional<User> user = userRepository.findById(id);
        log.info("user info:{}", user);
    }

    @Test
    @Ignore
    void updateUser(){
        int id = 3;
        User newUser = new User();
        newUser.setUserId(5);
        newUser.setPwd("111");
        newUser.setUname("updated");
        newUser.setLevel(5);

        userRepository.update(newUser);
    }

    @Test
    @Ignore
    void deleteUser(){

    }
}