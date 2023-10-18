package com.geekaca.mall.service.impl;

import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.mapper.UserMapper;
import com.geekaca.mall.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(MallUserRegisterParam mallUserRegisterParam) {
        Integer isRegisterOk = userMapper.insertUser(mallUserRegisterParam);
        return isRegisterOk == 1;
    }
}
