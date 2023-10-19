package com.geekaca.mall.service;

import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.front.param.MallUserRegisterParam;

public interface MallUserService {
    boolean register(MallUserRegisterParam mallUserRegisterParam);
    String login(MallUserLoginParam userLoginParam);
}
