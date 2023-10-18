package com.geekaca.mall.service.impl;

import com.geekaca.mall.common.Constants;
import com.geekaca.mall.controller.front.param.SaveCartItemParam;
import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.mapper.ShoppingCartItemMapper;
import com.geekaca.mall.service.ShoppingCartItemService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    @Autowired
    private ShoppingCartItemMapper cartItemMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public PageResult getMyShoppingCartItems(PageQueryUtil pageUtil) {
        List<ShoppingCartItemVO> myShoppingCartItems = cartItemMapper.getMyShoppingCartItems(pageUtil);
        int total = cartItemMapper.getTotalMyShoppingCartItems(pageUtil);
        PageResult pageResult = new PageResult(myShoppingCartItems, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveMallCartItem(SaveCartItemParam saveCartItemParam, long userId) {
        return null;
    }

    @Override
    public List<ShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId) {
        List<ShoppingCartItemVO> shoppingCartItemVOS = cartItemMapper.selectByUserId(mallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        return shoppingCartItemVOS;
    }

}


