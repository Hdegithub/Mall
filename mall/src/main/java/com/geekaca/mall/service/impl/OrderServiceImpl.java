package com.geekaca.mall.service.impl;


import com.geekaca.mall.common.NewBeeMallOrderStatusEnum;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.domain.Order;
import com.geekaca.mall.mapper.OrderMapper;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
    public String checkDone(Long[] ids) {
        //查询所有的订单 判断状态 修改状态和更新时间
        List<Order> orders = orderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String orderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (Order newBeeMallOrder : orders) {
                if (newBeeMallOrder.getIsDeleted() == 1) {
                    orderNos += newBeeMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (newBeeMallOrder.getOrderStatus() != 1) {
                    orderNos += newBeeMallOrder.getOrderNo() + " ";
                }
            }
            if (!StringUtils.hasText(orderNos)) {
                //订单状态正常 可以执行配货完成操作
                if (orderMapper.checkDone(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //订单此时不可执行出库操作
                if (orderNos.length() > 0 && orderNos.length() < 100) {
                    return orderNos + "订单的状态不是支付成功无法执行出库操作";
                } else {
                    return "你选择了太多状态不是支付成功的订单，无法执行配货完成操作";
                }
            }
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }


    @Override
    @Transactional
    public String checkOut(Long[] ids) {
        //查询所有的订单 判断状态 修改状态和更新时间
        List<Order> orders = orderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String orderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (Order newBeeMallOrder : orders) {
                if (newBeeMallOrder.getIsDeleted() == 1) {
                    orderNos += newBeeMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (newBeeMallOrder.getOrderStatus() != 1 && newBeeMallOrder.getOrderStatus() != 2) {
                    orderNos += newBeeMallOrder.getOrderNo() + " ";
                }
            }
            if (!StringUtils.hasText(orderNos)) {
                //订单状态正常 可以执行出库操作
                if (orderMapper.checkOut(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            }else {
                //订单此时不可执行出库操作
                if (orderNos.length() > 0 && orderNos.length() < 100) {
                    return orderNos + "订单的状态不是支付成功或配货完成无法执行出库操作";
                } else {
                    return "你选择了太多状态不是支付成功或配货完成的订单，无法执行出库操作";
                }
            }
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    public String closeOrder(Long[] ids) {
        List<Order> orders = orderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String orderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (Order newBeeMallOrder : orders) {
                // isDeleted=1 一定为已关闭订单
                if (newBeeMallOrder.getIsDeleted() == 1) {
                    orderNos += newBeeMallOrder.getOrderNo() + " ";
                    continue;
                }
                //已关闭或者已完成无法关闭订单
                if (newBeeMallOrder.getOrderStatus() == 4 || newBeeMallOrder.getOrderStatus() < 0) {
                    orderNos += newBeeMallOrder.getOrderNo() + " ";
                }
            }
            if (!StringUtils.hasText(orderNos)) {
                //订单状态正常 可以执行关闭操作
                if (orderMapper.closeOrder(Arrays.asList(ids), NewBeeMallOrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //订单此时不可执行关闭操作
                if (orderNos.length() > 0 && orderNos.length() < 100) {
                    return orderNos + "订单不能执行关闭操作";
                } else {
                    return "你选择的订单不能执行关闭操作";
                }
            }
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }
}
