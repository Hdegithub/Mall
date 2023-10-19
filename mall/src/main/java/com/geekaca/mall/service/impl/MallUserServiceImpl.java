package com.geekaca.mall.service.impl;

import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.mapper.UserMapper;
import com.geekaca.mall.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(MallUserRegisterParam mallUserRegisterParam) {
        List<MallUserRegisterParam> user = userMapper.findUser(mallUserRegisterParam.getLoginName());
        if (user == null) {
            Integer isRegisterOk = userMapper.insertUser(mallUserRegisterParam);
            return isRegisterOk == 1;
        }
        return false;
    }

    @Override
    public String login(MallUserLoginParam userLoginParam) {
        String longinUser = userMapper.findByNameAndPass(userLoginParam.getLoginName(), userLoginParam.getPasswordMd5());
        return longinUser;
    }
}
