package com.geekaca.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.geekaca.mall.common.Constants;
import com.geekaca.mall.common.NewBeeMallException;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.controller.front.param.SaveCartItemParam;
import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.controller.front.param.UpdateCartItemParam;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.domain.ShoppingCartItem;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.mapper.ShoppingCartItemMapper;
import com.geekaca.mall.service.ShoppingCartItemService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public String saveMallCartItem(SaveCartItemParam saveCartItemParam, Long userId) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(saveCartItemParam.getGoodsId());
        //商品不存在
        if (goodsInfo == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        BeanUtil.copyProperties(saveCartItemParam, shoppingCartItem);
        shoppingCartItem.setUserId(userId);
        if (cartItemMapper.insertSelective(shoppingCartItem) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public List<ShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId) {
        List<ShoppingCartItemVO> shoppingCartItemVOS = cartItemMapper.selectByUserId(mallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        return shoppingCartItemVOS;
    }

    @Override
    public String updateMallCartItem(UpdateCartItemParam updateCartItemParam, Long userId) {
        ShoppingCartItem shoppingCartItemUpdate = cartItemMapper.selectByPrimaryKey(updateCartItemParam.getCartItemId());
        if (shoppingCartItemUpdate == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        //超出单个商品的最大数量
        if (updateCartItemParam.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //当前登录账号的userId与待修改的cartItem中userId不同，返回错误
        if (!shoppingCartItemUpdate.getUserId().equals(userId)) {
            return ServiceResultEnum.NO_PERMISSION_ERROR.getResult();
        }
        //数值相同，则不执行数据操作
        if (updateCartItemParam.getGoodsCount().equals(shoppingCartItemUpdate.getGoodsCount())) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        shoppingCartItemUpdate.setGoodsCount(updateCartItemParam.getGoodsCount());
        shoppingCartItemUpdate.setUpdateTime(new Date());
        //修改记录
        if (cartItemMapper.updateByPrimaryKeySelective(shoppingCartItemUpdate) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean deleteById(Long cartItemId, Long userId) {
        ShoppingCartItem shoppingCartItem = cartItemMapper.selectByPrimaryKey(cartItemId);
        if (shoppingCartItem == null) {
            return false;
        }
        //userId不同不能删除
        if (!userId.equals(shoppingCartItem.getUserId())) {
            return false;
        }
        return cartItemMapper.deleteByPrimaryKey(cartItemId) > 0;
    }

}


