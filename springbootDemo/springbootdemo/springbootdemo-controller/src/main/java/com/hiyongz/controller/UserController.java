package com.hiyongz.controller;

import com.hiyongz.dao.dataobject.Result;
import com.hiyongz.dao.dataobject.User;
import com.hiyongz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 10287
 */
@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询部门数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping
    public Result list(){
        List<User> usertList =  userService.list();
        System.out.print(usertList);
        return Result.success(usertList);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }
}
