package com.geekaca.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.geekaca.mall.controller.vo.MallUserAddressVO;
import com.geekaca.mall.domain.UserAddress;
import com.geekaca.mall.mapper.UserAddressMapper;
import com.geekaca.mall.service.MallUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallUserAddressServiceImpl implements MallUserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<MallUserAddressVO> getAddresses(long userId) {
        List<UserAddress> userAddress = userAddressMapper.findAddressList(userId);
        List<MallUserAddressVO> mallUserAddressVO = new ArrayList<>();
        BeanUtil.copyProperties(userAddress,mallUserAddressVO);
        return mallUserAddressVO;
    }
}
