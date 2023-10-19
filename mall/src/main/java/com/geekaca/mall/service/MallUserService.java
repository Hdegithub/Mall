package com.geekaca.mall.service;

import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.domain.User;

public interface MallUserService {
    boolean register(MallUserRegisterParam mallUserRegisterParam);
    String login(MallUserLoginParam userLoginParam);

    User getUserById(long uidLong);
}
