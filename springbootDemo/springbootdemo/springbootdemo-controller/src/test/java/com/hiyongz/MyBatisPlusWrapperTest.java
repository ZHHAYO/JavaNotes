package com.hiyongz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserplusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * Date:2022/2/14
 * Author:ybc
 * Description:
 */
@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserplusMapper userplusMapper;

    @Test
    public void test01(){
        // 组装查询条件
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<Userplus> list = userplusMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        // 组装排序条件
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<Userplus> list = userplusMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03(){
        // 组装删除条件
        //删除邮箱地址为null的用户信息
        //UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userplusMapper.delete(queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    public void test04(){
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        // 条件的优先级
        //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("username", "a")
                .or()
                .isNull("email");
        Userplus user = new Userplus();
        user.setName("小明");
        user.setEmail("test@atguigu.com");
        int result = userplusMapper.update(user, queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    public void test04_2() {
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
        //lambda表达式内的逻辑优先运算
        queryWrapper
                .like("username", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        Userplus user = new Userplus();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userplusMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test05(){
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //lambda中的条件优先执行
        //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "a")
                .and(i->i.gt("age",20).or().isNull("email"));
        Userplus user = new Userplus();
        user.setName("小红");
        user.setEmail("test@atguigu.com");
        int result = userplusMapper.update(user, queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    public void test06(){
        // 组装select子句
        //查询用户的用户名、年龄、邮箱信息
        //SELECT user_name,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "age", "email");
        List<Map<String, Object>> maps = userplusMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        // 实现子查询
        //查询id小于等于100的用户信息
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 100))
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from t_user where id <= 100");
        List<Userplus> list = userplusMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08(){
        // UpdateWrapper
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        UpdateWrapper<Userplus> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("username", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("username", "小黑").set("email","abc@atguigu.com");
        int result = userplusMapper.update(null, updateWrapper);
        System.out.println("result："+result);
    }

    @Test
    public void test09(){
        // condition
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            //isNotBlank判断某个字符创是否不为空字符串、不为null、不为空白符
            queryWrapper.like("username", username);
        }
        if(ageBegin != null){
            queryWrapper.ge("age", ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.le("age", ageEnd);
        }
        List<Userplus> list = userplusMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<Userplus> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "username", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<Userplus> list = userplusMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11(){
        // LambdaQueryWrapper
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<Userplus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), Userplus::getName, username)
                .ge(ageBegin != null, Userplus::getAge, ageBegin)
                .le(ageEnd != null, Userplus::getAge, ageEnd);
        List<Userplus> list = userplusMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test12(){
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        LambdaUpdateWrapper<Userplus> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(Userplus::getName, "a")
                .and(i -> i.gt(Userplus::getAge, 20).or().isNull(Userplus::getEmail));
        updateWrapper.set(Userplus::getName, "小黑").set(Userplus::getEmail,"abc@atguigu.com");
        int result = userplusMapper.update(null, updateWrapper);
        System.out.println("result："+result);
    }

}
