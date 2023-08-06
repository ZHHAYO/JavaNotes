package com.hiyongz.service.impl;

import com.hiyongz.common.utils.JwtUtil;
import com.hiyongz.common.utils.RedisCache;
import com.hiyongz.dao.dataobject.LoginUser;
import com.hiyongz.dao.dataobject.ResponseResult;
import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.service.Login2Servcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class Login2ServiceImpl  implements Login2Servcie {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(Userplus userplus) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userplus.getUsername(),userplus.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUserplus().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        System.out.println("=================================================");
        System.out.println(userid);
        System.out.println(jwt);
        System.out.println("=================================================");
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis  userid作为key
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = Long.valueOf(loginUser.getUserplus().getId());
        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"注销成功");
    }
}
