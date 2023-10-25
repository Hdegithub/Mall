package com.geekaca.mall.service;

import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

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
    Boolean checkDone(Long[] ids);

    /**
     * 出库
     *
     * @param ids
     * @return
     */
    Boolean checkOut(Long[] ids);
}
