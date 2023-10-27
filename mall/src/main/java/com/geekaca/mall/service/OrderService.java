package com.geekaca.mall.service;

import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.controller.vo.OrderDetailVO;
import com.geekaca.mall.domain.UserAddress;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

import java.util.List;

public interface OrderService {
    /**
     * 订单管理列表显示
     *
     * @param
     * @return
     */
    PageResult getOrdersPage(PageQueryUtil pageUtil);

    /**
     * 配货
     *
     * @param ids
     * @return
     */
    String checkDone(Long[] ids);

    /**
     * 出库
     *
     * @param ids
     * @return
     */
    String checkOut(Long[] ids);

    String closeOrder(Long[] ids);

    String saveOrder(Long userId, UserAddress address, List<ShoppingCartItemVO> itemsForSave);

    String paySuccess(String orderNo, int payType);

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @param userId
     * @return
     */
    OrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     * 我的订单列表
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyOrders(PageQueryUtil pageUtil);
}
