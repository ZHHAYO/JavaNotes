package com.hiyongz;

import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserplusMapper;
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
    public void testSelectList() {
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<Userplus> list = userplusMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        LocalDateTime now = LocalDateTime.now();
        Userplus userplus = new Userplus((Integer) null, "zhangsan", "123456", "张三", (short) 1, "zhangsan@atguigu.com", "", "", 1, "", now, now);
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
        int result = userplusMapper.deleteById(1475754982694199298L);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testDeleteBatchIds(){
        //通过多个id批量删除
        // DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
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

}
