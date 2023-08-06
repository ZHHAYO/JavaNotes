package com.hiyongz.controller;

import com.hiyongz.dao.dataobject.ResponseResult;
import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.service.Login2Servcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login2Controller {
    @Autowired
    private Login2Servcie login2Servcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody Userplus userplus){
        //登录
        return login2Servcie.login(userplus);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return login2Servcie.logout();
    }
}
