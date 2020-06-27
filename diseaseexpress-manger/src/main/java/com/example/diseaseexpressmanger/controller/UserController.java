package com.example.diseaseexpressmanger.controller;

import com.example.diseaseexpressmanger.model.User;
import com.example.diseaseexpressmanger.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /**
     * U1.新建用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/add")
    public Map<String, Object> addUser(@RequestBody(required = false) User user){
        //登录 权限控制
        Map<String,Object> resultMap = new HashMap<>();

        System.out.println(user);

        resultMap.put("status", 200);
        resultMap.put("msg", "添加用户成功");
        resultMap.put("date", userService.addUser(user));
        return resultMap;
    }

    /**
     * U2.查询用户（id）
     *
     * @param userId
     * @return
     */
    @GetMapping("/{user_id}")
    public Map<String, Object> getUserById(@PathVariable("user_id") Integer userId){
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "查询用户成功");
        resultMap.put("data", userService.findUserById(userId));
        return resultMap;
    }

    /**
     * U3.查询用户（info）
     *
     * @param uname
     * @param place
     * @param level
     * @return
     */
    @GetMapping("/query")
    public Map<String,Object> getUserByInfo(@RequestParam(name = "uname", required = false) String uname,
                                            @RequestParam(name = "place", required = false) String place,
                                            @RequestParam(name = "level", required = false) Integer level){
        Map<String,Object> resultMap = new HashMap<>();

        User queryUser = new User();
        queryUser.setUname(uname);
        queryUser.setPlace(place);
        queryUser.setLevel(level);

        resultMap.put("status", 200);
        resultMap.put("msg", "查询用户成功");
        resultMap.put("data", userService.findUserByInfo(queryUser));
        return resultMap;
    }

    /**
     * U4.更新用户信息
     *
     * @param newUser
     * @return
     */
    @PostMapping("/update")
    public Map<String,Object> updateUser(@RequestBody(required = false) User newUser){
        Map<String,Object> resultMap = new HashMap<>();

        if(userService.updateUser(newUser)){
            resultMap.put("status", 200);
            resultMap.put("msg", "更新用户成功");
            resultMap.put("data", null);
        }

        return resultMap;
    }

    /**
     * U5.删除用户
     *
     * @param userId
     * @return
     */
    @GetMapping("/delete/{user_id")
    public Map<String,Object> delteUser(@PathVariable(name = "user_id", required = true) Integer userId){
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("status", 200);
        resultMap.put("msg", "删除用户成功");
        resultMap.put("data", userService.deleteUser(userId));
        return resultMap;
    }

}
