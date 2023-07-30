package com.hiyongz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hiyongz.dao.dataobject.Userplus;
import com.hiyongz.dao.mapper.UserplusMapper;
import com.hiyongz.service.UserplusService;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl实现了IService，提供了IService中基础功能的实现
 * 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现
 */
@Service
public class UserplusServiceImpl extends ServiceImpl<UserplusMapper, Userplus> implements UserplusService {

}
