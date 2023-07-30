package com.hiyongz.controller;

import com.hiyongz.dao.dataobject.PageBean;
import com.hiyongz.dao.dataobject.Result;
import com.hiyongz.dao.dataobject.User;
import com.hiyongz.service.UserService;
import com.hiyongz.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 10287
 */
@Api(tags = "用户接口")
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
//    @GetMapping
//    public Result list(){
//        List<User> usertList =  userService.list();
//        System.out.print(usertList);
//        return Result.success(usertList);
//    }

    @ApiOperation(value = "查询用户", notes = "分页查询用户")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        //调用service分页查询
        PageBean pageBean = userService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }


    @ApiOperation(value = "查询用户", notes = "通过ID查询用户")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
        //  return Result.success(user);
    }

    @ApiOperation(value = "更新用户", notes = "更新员工信息")
    @PutMapping
    public Result update(@RequestBody User user){
        // log.info("更新员工信息 : {}", user);
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        // log.info("批量删除操作, ids:{}",ids);
        userService.delete(ids);
        return Result.success();
    }
}
