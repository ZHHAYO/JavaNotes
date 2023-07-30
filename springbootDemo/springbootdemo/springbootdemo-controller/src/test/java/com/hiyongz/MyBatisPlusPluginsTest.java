package com.hiyongz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserplusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserplusMapper userplusMapper;

    @Test
    public void testPage(){
        Page<Userplus> page = new Page<>(2, 3);
        userplusMapper.selectPage(page, null);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testPageVo(){
        Page<Userplus> page = new Page<>(1, 3);
        userplusMapper.selectPageVo(page, 20);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }
}
