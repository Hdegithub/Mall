package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.OrderDetailVO;
import com.geekaca.mall.domain.Order;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

public interface OrderService {
    PageResult getOrdersPage(PageQueryUtil pageUtil);

    OrderDetailVO getOrderByOrderId(Long orderId);
}
