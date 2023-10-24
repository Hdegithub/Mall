package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.OrderDetailVO;

public interface ItemService {
    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    OrderDetailVO getItemByOrderId(Long orderId);
}
