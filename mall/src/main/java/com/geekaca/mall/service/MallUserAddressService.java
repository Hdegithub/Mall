package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.MallUserAddressVO;

import java.util.List;

public interface MallUserAddressService {

    List<MallUserAddressVO> getAddresses(long userId);
}
