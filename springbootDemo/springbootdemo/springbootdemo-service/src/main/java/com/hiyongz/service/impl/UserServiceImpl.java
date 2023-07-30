package com.hiyongz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hiyongz.dao.dataobject.PageBean;
import com.hiyongz.dao.dataobject.User;
import com.hiyongz.dao.mapper.UserMapper;
import com.hiyongz.service.UserService;
import com.hiyongz.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

//    @Override
//    public List<User> list() {
//        return userMapper.list();
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<User> empList = userMapper.list(name, gender, begin, end);
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(empList, userVO);
        Page<User> p = (Page<User>) empList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
    }

    @Override
    public void delete(List<Integer> ids) {
        userMapper.delete(ids);
    }

    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        //查询用户信息
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,username);
//        User user = userMapper.selectOne(queryWrapper);
//        //如果没有查询到用户就抛出异常
//        if(Objects.isNull(user)){
//            throw new RuntimeException("用户名或者密码错误");
//        }
//
//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
////        List<String> list = menuMapper.selectPermsByUserId(user.getId());
//        //把数据封装成UserDetails返回
//        return new LoginUser(user,list);
//    }

}
