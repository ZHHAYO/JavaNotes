package com.hiyongz.service.impl;

import com.hiyongz.dao.dataobject.Dept;
import com.hiyongz.dao.mapper.DeptMapper;
import com.hiyongz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
}
