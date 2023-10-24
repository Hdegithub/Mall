package com.geekaca.mall.service;

import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

public interface OrderService {
    PageResult getOrdersPage(PageQueryUtil pageUtil);
}
