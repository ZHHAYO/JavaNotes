package com.hiyongz.dao.mapper;

import com.hiyongz.dao.dataobject.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //在运行时,会自动生成该接口的实现类对象(代理对象), 并且将该对象交给IOC容器管理
public interface UserMapper {

    /**
     * 查询全部用户信息
     */
    //    @Select("select * from user")
    public List<User> list();

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    //    @Select("select * from user where  id = #{id}")
    User getById(Integer id);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    /**
     * 新增员工
     * @param user
     */
    @Insert("insert into user(username, name, gender, phone, email, dept_id, role, create_time, update_time) " +
            " values(#{username},#{name},#{gender},#{phone},#{email},#{deptId},#{role},#{createTime},#{updateTime})")
    void insert(User user);


    /**
     * 更新员工
     * @param user
     */
    void update(User user);

    /**
     * 根据用户名和密码查询员工
     * @param user
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);

    /**
     * 根据部门ID删除该部门下的员工数据
     * @param deptId
     */
    @Delete("delete from user where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
