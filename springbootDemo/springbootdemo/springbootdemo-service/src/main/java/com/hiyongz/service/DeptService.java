package com.hiyongz.service;

import com.hiyongz.dao.dataobject.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门
     * @return
     */
    List<Dept> list();
}
