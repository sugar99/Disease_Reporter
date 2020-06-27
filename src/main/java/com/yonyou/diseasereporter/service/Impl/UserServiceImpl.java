package com.yonyou.diseasereporter.service.Impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yonyou.diseasereporter.model.User;
import com.yonyou.diseasereporter.repository.UserRepository;
import com.yonyou.diseasereporter.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;


    @Override
    public User addUser(User newUser) {
        User userAdded = userRepository.save(newUser);
        return userAdded;
    }

    @Override
    public User findUserById(Integer userId) {
        Optional<User> optional = userRepository.findById(userId);
        User userFound = (optional.get() == null) ? new User() : optional.get();
        return userFound;
    }

    @Override
    public List<User> findUserByInfo(User userInfo) {
        List<User> list;
        list = userRepository.findByInfo(userInfo.getUname(),userInfo.getPlace(),userInfo.getLevel());

        return list;
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }

    @Override
    public User deleteUser(Integer userId) {
        return null;
    }
}
