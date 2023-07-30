package com.hiyongz.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hiyongz.dao.dataobject.Userplus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserplusMapper  extends BaseMapper<Userplus> {
    /**
     * 通过年龄查询用户信息并分页
     * @param page MyBatis-Plus所提供的分页对象，必须位于第一个参数的位置
     * @param age
     * @return
     */
    Page<Userplus> selectPageVo(@Param("page") Page<Userplus> page, @Param("age") Integer age);
}
