package com.geekaca.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.geekaca.mall.common.NewBeeMallException;
import com.geekaca.mall.common.NewBeeMallOrderStatusEnum;
import com.geekaca.mall.common.PayTypeEnum;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.controller.vo.OrderDetailVO;
import com.geekaca.mall.controller.vo.OrderItemVO;
import com.geekaca.mall.domain.Order;
import com.geekaca.mall.domain.OrderItem;
import com.geekaca.mall.mapper.OrderItemMapper;
import com.geekaca.mall.mapper.OrderMapper;
import com.geekaca.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDetailVO getItemByOrderId(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            NewBeeMallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getOrderId());
        List<OrderItemVO> orderItemVOS = BeanUtil.copyToList(orderItems, OrderItemVO.class);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtil.copyProperties(order, orderDetailVO);
        orderDetailVO.setOrderStatusString(NewBeeMallOrderStatusEnum.getNewBeeMallOrderStatusEnumByStatus(orderDetailVO.getOrderStatus()).getName());
        orderDetailVO.setPayTypeString(PayTypeEnum.getPayTypeEnumByType(orderDetailVO.getPayType()).getName());
        orderDetailVO.setMallOrderItemVOS(orderItemVOS);
        return orderDetailVO;
    }
}
