package com.hiyongz.service;

import com.hiyongz.dao.dataobject.Dept;
import com.hiyongz.dao.dataobject.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门
     * @return
     */
    List<Dept> list();
}
