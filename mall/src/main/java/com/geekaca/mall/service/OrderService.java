package com.geekaca.mall.service;

import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.domain.UserAddress;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

import java.util.List;

public interface OrderService {
    PageResult getOrdersPage(PageQueryUtil pageUtil);

    String saveOrder(Long userId, UserAddress address, List<ShoppingCartItemVO> itemsForSave);

    String paySuccess(String orderNo, int payType);
}
