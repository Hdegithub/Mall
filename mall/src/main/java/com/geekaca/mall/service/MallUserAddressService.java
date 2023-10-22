package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.MallUserAddressVO;
import com.geekaca.mall.domain.UserAddress;

import java.util.List;

public interface MallUserAddressService {

    List<MallUserAddressVO> getAddresses(long userId);

    UserAddress getUserAddressById(Long addressId);
    UserAddress getMyDefaultAddressByUserId(long userId);

    Boolean saveUserAddress(UserAddress userAddress);

    UserAddress getMallUserAddressById(Long addressId);

    Boolean updateMallUserAddress(UserAddress userAddress);

    Boolean deleteById(Long addressId);
}
