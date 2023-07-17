package com.hiyongz.dao.mapper;

import com.hiyongz.dao.dataobject.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全部用户信息
    @Select("select * from dept")
    public List<Dept> list();
}
