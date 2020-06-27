package com.example.diseaseexpressmanger.service.impl;


import com.example.diseaseexpressmanger.model.User;
import com.example.diseaseexpressmanger.repository.UserRepository;
import com.example.diseaseexpressmanger.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
        User userFound = optional.isPresent() ? optional.get() : new User();
        return userFound;
    }

    @Override
    public List<User> findUserByInfo(User userInfo) {
        List<User> list;
        list = userRepository.findByInfo(userInfo.getUname(),userInfo.getPlace(),userInfo.getLevel());

        return list;
    }

    @Override
    public boolean updateUser(User newUser) {
        User old = userRepository.findById(newUser.getUserId()).get();
        if(newUser.getUname()!=null && !"".equals(newUser.getUname())){
            old.setUname(newUser.getUname());
        }
        if(newUser.getPwd()!=null && !"".equals(newUser.getPwd())){
            old.setPwd(newUser.getPwd());
        }
        if(newUser.getPlace()!=null && !"".equals(newUser.getPlace())){
            old.setPlace(newUser.getPlace());
        }
        if(newUser.getLevel()!=null && !"".equals(newUser.getLevel())){
            old.setLevel(newUser.getLevel());
        }

        userRepository.save(old);
        return true;
    }

    @Override
    public User deleteUser(Integer userId) {
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
            userRepository.deleteById(userId);
            return optional.get();
        }

        return new User();
    }
}
