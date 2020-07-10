package com.springboot.controller;

import java.util.List;

import com.springboot.entity.BookRankList;
import com.springboot.entity.UserRankList;
import com.springboot.service.RankListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RequestMapping("/api/rank")
@RestController
public class RankListController {
    @Autowired
    private RankListService service;

    @GetMapping("/getBookRank")
    @ResponseBody
    public List<BookRankList> getBookRankList() {
        return service.getBookRankList();
    }

    @GetMapping("/getUserRank")
    @ResponseBody
    public List<UserRankList> getUserRankList() {
        return service.getUserRankList();
    }
}