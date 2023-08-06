package com.hiyongz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hiyongz.dao.dataobject.LoginUser;
import com.hiyongz.dao.dataobject.User;
import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserMapper;
import com.hiyongz.dao.mapper.UserplusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserplusMapper userplusMapper;

//    @Autowired
//    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<Userplus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Userplus::getUsername,username);
        Userplus userplus = userplusMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(userplus)){
            throw new RuntimeException("用户名或者密码错误");
        }

        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
//        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //把数据封装成UserDetails返回
        return new LoginUser(userplus,list);
    }
}
