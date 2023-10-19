package com.geekaca.mall.service;

import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.controller.front.param.MallUserUpdateParam;
import com.geekaca.mall.domain.User;
import com.geekaca.mall.utils.PageResult;

public interface MallUserService {
    boolean register(MallUserRegisterParam mallUserRegisterParam);
    String login(MallUserLoginParam userLoginParam);

    User getUserById(long uidLong);

    boolean logout(long uidLong);

    PageResult findUsers(Integer pageNo, Integer pageSize);

    boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam, long uidLong);
}
