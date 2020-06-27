package com.yonyou.diseasereporter.service;

import com.yonyou.diseasereporter.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface UserService {

    public User addUser(User newUser);

    public User findUserById(Integer userId);

    public List<User> findUserByInfo(User userInfo);

    public boolean updateUser(User user);

    public User deleteUser(Integer userId);
}
