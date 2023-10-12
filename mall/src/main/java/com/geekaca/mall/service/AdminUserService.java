package com.geekaca.mall.service;

import com.geekaca.mall.controller.admin.param.AdminLoginParam;

public interface AdminUserService {
    /**
     * 执行登陆时候，通常都需要 做：
     * 1，校验登陆
     *  1.1 登陆成功后，要生成token ，JWT
     * 2，把用户信息查出来（查询userID）
     */
    /**
     * 校验登陆
     * @param adminLoginParam
     * @return token
     */
    String login(AdminLoginParam adminLoginParam);
}
