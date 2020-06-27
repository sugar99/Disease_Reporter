package com.example.diseaseexpressmanger.service;


import com.example.diseaseexpressmanger.model.User;

import java.util.List;


public interface UserService {

    public User addUser(User newUser);

    public User findUserById(Integer userId);

    public List<User> findUserByInfo(User userInfo);

    public boolean updateUser(User user);

    public User deleteUser(Integer userId);
}
