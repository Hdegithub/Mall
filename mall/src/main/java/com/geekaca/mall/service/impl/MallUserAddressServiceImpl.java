package com.geekaca.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.geekaca.mall.controller.vo.MallUserAddressVO;
import com.geekaca.mall.domain.UserAddress;
import com.geekaca.mall.exceptions.LoginNameExsistsException;
import com.geekaca.mall.mapper.UserAddressMapper;
import com.geekaca.mall.service.MallUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallUserAddressServiceImpl implements MallUserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<MallUserAddressVO> getAddresses(long userId) {
        List<UserAddress> userAddress = userAddressMapper.findAddressList(userId);
        List<MallUserAddressVO> mallUserAddressVO = BeanUtil.copyToList(userAddress,MallUserAddressVO.class );
        return mallUserAddressVO;
    }

    @Override
    public UserAddress getUserAddressById(Long addressId) {
        UserAddress addressList = userAddressMapper.searchAddressById(addressId);
        return addressList;
    }

    @Override
    public UserAddress getMyDefaultAddressByUserId(long userId) {
        UserAddress myDefaultAddress = userAddressMapper.getMyDefaultAddress(userId);
        return myDefaultAddress;
    }

    @Override
    public Boolean saveUserAddress(UserAddress userAddress) {
        if(userAddress.getDefaultFlag() != 0){
            int defaultAddressCount = userAddressMapper.changeDefaultAddressFlag(userAddress.getUserId());
        }
        int userCount = userAddressMapper.insertSelective(userAddress);
        if(userCount == 1){
            return true;
        }else {
            throw new LoginNameExsistsException("用户地址添加异常");
        }
    }

    @Override
    public UserAddress getMallUserAddressById(Long addressId) {
        UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
        return userAddress;
    }

    @Override
    public Boolean updateMallUserAddress(UserAddress userAddress) {
        if(userAddress.getDefaultFlag() != 0){
            int defaultAddressCount = userAddressMapper.changeDefaultAddressFlag(userAddress.getUserId());
        }
        int userUpdateCount = userAddressMapper.updateByPrimaryKeySelective(userAddress);
        if(userUpdateCount == 1){
            return true;
        }else {
            throw new LoginNameExsistsException("用户地址修改异常");
        }
    }

    @Override
    public Boolean deleteById(Long addressId) {
        int userDelCount = userAddressMapper.deleteByPrimaryKey(addressId);
        if(userDelCount == 1){
            return true;
        }else {
            throw new LoginNameExsistsException("用户地址删除异常");
        }

    }
}
