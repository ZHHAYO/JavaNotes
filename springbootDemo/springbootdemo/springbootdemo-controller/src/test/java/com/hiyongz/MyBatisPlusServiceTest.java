package com.hiyongz;

import com.hiyongz.dao.dataobject.Userplus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hiyongz.service.UserplusService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class MyBatisPlusServiceTest {
    @Autowired
    private UserplusService userplusService;

    @Test
    public void testGetCount(){
        //查询总记录数
        //SELECT COUNT( * ) FROM user
        long count = userplusService.count();
        System.out.println("总记录数："+count);
    }

    @Test
    public void testSaveBatch(){
        // SQL长度有限制，海量数据插入单条SQL无法实行，
        // 因此MP将批量插入放在了通用Service中实现，而不是通用Mapper
        ArrayList<Userplus> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Userplus user = new Userplus();
            user.setName("user" + i);
            user.setPassword("test" + i);
            users.add(user);
        }
        //SQL:INSERT INTO t_user ( username, age ) VALUES ( ?, ? )
        userplusService.saveBatch(users);
    }
}
