package com.springboot.controller;

import java.util.List;

import com.springboot.entity.BookRankList;
import com.springboot.entity.User;
import com.springboot.entity.UserRankList;
import com.springboot.service.RankListService;
import com.springboot.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RequestMapping("/api/user")
@RestController
public class UserInfoController {
    @Autowired
    private UserService service;

    @GetMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(@RequestParam("userName") String userName) {
        return service.getUserInfo(userName);
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public boolean updateUserInfo(@RequestBody User user) {
        return service.updateUserInfo(user);
    }
}