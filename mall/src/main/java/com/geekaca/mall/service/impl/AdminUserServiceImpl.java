package com.geekaca.mall.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.geekaca.mall.controller.admin.param.AdminLoginParam;
import com.geekaca.mall.domain.AdminUser;
import com.geekaca.mall.mapper.AdminUserMapper;
import com.geekaca.mall.service.AdminUserService;
import com.geekaca.mall.utils.JedisPoolUtil;
import com.geekaca.mall.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public String login(AdminLoginParam adminLoginParam) {
        //需要把用户传递的密码 md5()
//        String passMd5 = SecureUtil.md5(adminLoginParam.getPasswordMd5());
//        adminLoginParam.setPasswordMd5(passMd5);
        AdminUser adminUser = adminUserMapper.checkLogin(adminLoginParam.getUserName(), adminLoginParam.getPasswordMd5());
        if (adminUser == null){
            //登陆失败
            return null;
        }
        //生成token
        //Long -》String
        String token = JwtUtil.createToken(adminUser.getAdminUserId().toString(), adminUser.getLoginUserName());
        //把用户信息缓存到token
        try(Jedis jedis = JedisPoolUtil.getJedis();) {
            String key = "uid:admin:" + adminUser.getAdminUserId();
            jedis.set(key, token);
            //设置 指定key的过期时间（效果，到时间后，这个key value对会被自动删除）
            jedis.expire(key, 60 * 60 * 3);
        }
        return token;
    }
}
