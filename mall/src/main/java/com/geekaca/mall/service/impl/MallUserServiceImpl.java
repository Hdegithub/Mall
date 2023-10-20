package com.geekaca.mall.service.impl;

import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.controller.front.param.MallUserUpdateParam;
import com.geekaca.mall.domain.User;
import com.geekaca.mall.exceptions.LoginNameExsistsException;
import com.geekaca.mall.mapper.UserMapper;
import com.geekaca.mall.service.MallUserService;
import com.geekaca.mall.utils.JwtUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(MallUserRegisterParam mallUserRegisterParam) {
        //验证用户名是否已经被占用
        Integer userCount = userMapper.findUser(mallUserRegisterParam.getLoginName());
        if (userCount == null) {
            Integer isRegisterOk = userMapper.insertUser(mallUserRegisterParam);
            return isRegisterOk == 1;
        } else {
            //说明用户名已经被占用, 抛出自定义异常  用户名已经被占用
            throw new LoginNameExsistsException("用户名已经被占用!");
        }

    }

    @Override
    public String login(MallUserLoginParam userLoginParam) {
        User user = userMapper.userCheckLogin(userLoginParam.getLoginName(), userLoginParam.getPasswordMd5());
        if (user == null) {
            //登陆失败
            return null;
        }
        //生成token
        String token = JwtUtil.createToken(user.getUserId().toString(), user.getLoginName());
        return token;
    }

    @Override
    public User getUserById(long uidLong) {
        User userById = userMapper.findUserById(uidLong);
        return userById;
    }

    @Override
    public boolean logout(long uidLong) {
        User userById = userMapper.findUserById(uidLong);
        if (userById != null) {
            return true;
        } else {
            throw new LoginNameExsistsException("用户登出异常");
        }
    }

    @Override
    public PageResult findUsers(Integer pageNo, Integer pageSize) {
        List<User> userList = userMapper.findUserList((pageNo - 1) * pageSize, pageSize);
        int userCount = userMapper.findUserCount();
        PageResult pageResult = new PageResult(userList, userCount, pageSize, pageNo);
        return pageResult;
    }

    @Override
    public boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam, long uidLong) {
        User updateUser = new User();
        updateUser.setNickName(mallUserUpdateParam.getNickName());
        updateUser.setPasswordMd5(mallUserUpdateParam.getPasswordMd5());
        updateUser.setIntroduceSign(mallUserUpdateParam.getIntroduceSign());
        int updateById = userMapper.updateById(updateUser, uidLong);

        if (updateById > 0) {
            return true;
        } else {
            return false;
        }

    }
}
