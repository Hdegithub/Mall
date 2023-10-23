package com.geekaca.mall.service;

import com.geekaca.mall.controller.front.param.SaveCartItemParam;
import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.controller.front.param.UpdateCartItemParam;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

import java.util.List;

public interface ShoppingCartItemService {

    /**
     * 我的购物车(分页数据)
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyShoppingCartItems(PageQueryUtil pageUtil);

    /**
     * 保存商品至购物车中
     *
     * @param saveCartItemParam
     * @param userId
     * @return
     */
    String saveMallCartItem(SaveCartItemParam saveCartItemParam, Long userId);

    /**
     * 获取我的购物车中的列表数据
     *
     * @param mallUserId
     * @return
     */
    List<ShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId);


    /**
     * 修改购物车中的属性
     * @param updateCartItemParam
     * @param userId
     * @return
     */
    String updateMallCartItem(UpdateCartItemParam updateCartItemParam, Long userId);

    /**
     * 删除购物车中的商品
     *
     * @param cartItemId
     * @param userId
     * @return
     */
    Boolean deleteById(Long cartItemId, Long userId);
}
