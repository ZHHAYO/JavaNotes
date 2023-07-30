package com.hiyongz;

import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.enums.GenderEnum;
import com.hiyongz.dao.mapper.UserplusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusEnumTest {
    @Autowired
    private UserplusMapper userplusMapper;

    @Test
    public void test(){
        Userplus userplus = new Userplus();
        userplus.setName("admin");
        userplus.setAge(33);
        userplus.setGender(GenderEnum.MALE);
        int result = userplusMapper.insert(userplus);
        System.out.println("result:"+result);
    }
}
