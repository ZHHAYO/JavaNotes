package com.hiyongz.service;

import com.hiyongz.dao.dataobject.ResponseResult;
import com.hiyongz.dao.dataobject.Userplus;

public interface Login2Servcie {
    ResponseResult login(Userplus userplus);

    ResponseResult logout();
}
