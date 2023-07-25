package com.hiyongz.controller;

import com.hiyongz.common.utils.GithubUtils;
import com.hiyongz.dao.dataobject.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Value("${github.url}")
    private String githubUrl;

    @Autowired
    private GithubUtils githubUtils;

    @GetMapping("/value")
    public Result getById(){
        return Result.success(githubUrl);
    }

    @GetMapping("/value2")
    public Result getById2(){
        String url = githubUtils.getGithubUrl();
        return Result.success(url);
    }


}
