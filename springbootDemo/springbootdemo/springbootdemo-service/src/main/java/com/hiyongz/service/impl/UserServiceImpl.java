package com.hiyongz.service.impl;

import com.hiyongz.dao.dataobject.User;
import com.hiyongz.dao.mapper.UserMapper;
import com.hiyongz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
}
