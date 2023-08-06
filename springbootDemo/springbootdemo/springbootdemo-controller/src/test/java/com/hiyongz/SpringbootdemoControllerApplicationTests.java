package com.hiyongz;

import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.enums.GenderEnum;
import com.hiyongz.dao.mapper.UserplusMapper;
import com.hiyongz.service.UserplusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest
class SpringbootdemoControllerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserplusMapper userplusMapper;

    @Test
    public void testInsert(){
        LocalDateTime now = LocalDateTime.now();
//        Userplus userplus = new Userplus((Integer) null, "lishi", "123456", "张三", (short) 1, "zhangsan@atguigu.com", "", "", 1, "", now, now, "0");
        Userplus userplus = new Userplus((Integer) null, "zhangsan", "123456", "张三", GenderEnum.MALE, 15, "", "", "zhangsan@atguigu.com", 1, "", now, now, "0");
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userplusMapper.insert(userplus);
        System.out.println("受影响行数："+result);
        //1475754982694199298
        System.out.println("id自动获取："+userplus.getId());
    }

    @Test
    public void testDeleteById(){
        //通过id删除用户信息
        // DELETE FROM user WHERE id=?
        int result = userplusMapper.deleteById(13);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testDeleteBatchIds(){
        //通过多个id批量删除
        // DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> idList = Arrays.asList(14L, 16L);
        int result = userplusMapper.deleteBatchIds(idList);
        System.out.println("受影响行数："+result);
    }

    @Test public void testDeleteByMap(){
        //根据map集合中所设置的条件删除记录
        // DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 23);
        map.put("name", "张三");
        int result = userplusMapper.deleteByMap(map);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testUpdateById(){
//        Userplus userplus = new Userplus(4L, "admin", 22, null);
        LocalDateTime now = LocalDateTime.now();
        Userplus userplus = new Userplus(17, "lisi", "12345678", "李四", GenderEnum.MALE, 12, "","", "zhangsan@atguigu.com", 1, "", now, now,"0");
//        UPDATE user SET name=?, age=? WHERE id=?
        int result = userplusMapper.updateById(userplus);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testSelectById(){
        //根据id查询用户信息
        // SELECT id,name,age,email FROM user WHERE id=?
        Userplus userplus = userplusMapper.selectById(4L);
        System.out.println(userplus);
    }

    @Test
    public void testSelectBatchIds(){
        //根据多个id查询多个用户信息
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
        List<Long> idList = Arrays.asList(4L, 5L);
        List<Userplus> list = userplusMapper.selectBatchIds(idList);
        list.forEach(System.out::println);
    }

    @Test public void testSelectByMap(){
        //通过map条件查询用户信息
        // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Tang Seng");
        map.put("name", "唐僧");
        List<Userplus> list = userplusMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectList() {
        //查询所有用户信息
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<Userplus> list = userplusMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
