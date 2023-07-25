package com.hiyongz.service;

import com.hiyongz.dao.dataobject.PageBean;
import com.hiyongz.dao.dataobject.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    /**
     * 查询全部用户数据
     * @return
     */
//    List<User> list();

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);


    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 更新员工
     * @param user
     */
    void update(User user);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
