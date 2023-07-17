package com.hiyongz.service;

import com.hiyongz.dao.dataobject.User;

import java.util.List;

public interface UserService {
    /**
     * 查询全部用户数据
     * @return
     */
    List<User> list();

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User getById(Integer id);
}
