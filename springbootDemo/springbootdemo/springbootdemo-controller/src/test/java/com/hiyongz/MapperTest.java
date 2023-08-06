package com.hiyongz;

import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserplusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserplusMapper userplusMapper;

    @Test
    public void testUserMapper(){
        List<Userplus> users = userplusMapper.selectList(null);
        System.out.println(users);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestBCryptPasswordEncoder(){

//        $2a$10$npv5JSeFR6/wLz8BBMmSBOMb8byg2eyfK4/vvoBk3RKtTLBhIhcpy

        String encode = passwordEncoder.encode("123456");
//        String encode2 = passwordEncoder.encode("1234");
        System.out.println(encode);
//        System.out.println(encode2);
        System.out.println(passwordEncoder.
        matches("1234",
                "$2a$10$1r86e6DwxC6lw.eeKfArRuOAA2c57a.HrjimQ/7r0VW1KLX8r8BCq"));

    }
}
