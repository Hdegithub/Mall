package com.geekaca.mall.service.impl;


import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.domain.Order;
import com.geekaca.mall.mapper.OrderMapper;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements com.geekaca.mall.service.OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageResult getOrdersPage(PageQueryUtil pageUtil) {
        List<Order> orderList = orderMapper.findOrderList(pageUtil);
        int totalOrders = orderMapper.getTotalOrders(pageUtil);
        PageResult pageResult = new PageResult(orderList, totalOrders, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }


    @Override
    @Transactional
    public Boolean checkDone(Long[] ids) {
        int i = orderMapper.checkDone(Arrays.asList(ids));
        if (i>0){
            return true;
        }else {
            return false;
        }
    }


    @Override
    @Transactional
    public Boolean checkOut(Long[] ids) {
        int i = orderMapper.checkOut(Arrays.asList(ids));
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}
