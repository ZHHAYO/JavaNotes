package com.hiyongz;

import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserplusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusAnnotationTest {
    @Autowired
    private UserplusMapper userplusMapper;

    @Test
    public void testDeleteById(){
        //通过id删除用户信息
        int result = userplusMapper.deleteById(17);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testSelectById(){
        //根据id查询用户信息
        Userplus userplus = userplusMapper.selectById(17L);
        System.out.println(userplus);
    }
}
