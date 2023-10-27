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
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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

    @Override
    public List<ShoppingCartItemVO> getCartItemsForSettle(List<Long> cartItemIds,Long userId) {
        List<ShoppingCartItemVO> mallShoppingCartItemVOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(cartItemIds)) {
            NewBeeMallException.fail("购物项不能为空");
        }
        //查询出来 此次要结算的订单商品列表
        List<ShoppingCartItem> newBeeMallShoppingCartItems = cartItemMapper.selectByUserIdAndCartItemIds(userId, cartItemIds);
        if (CollectionUtils.isEmpty(newBeeMallShoppingCartItems)) {
            NewBeeMallException.fail("购物项不能为空");
        }
        if (newBeeMallShoppingCartItems.size() != cartItemIds.size()) {
            NewBeeMallException.fail("参数异常");
        }
        return getNewBeeMallShoppingCartItemVOS(mallShoppingCartItemVOS, newBeeMallShoppingCartItems);
    }

    private List<ShoppingCartItemVO> getNewBeeMallShoppingCartItemVOS(List<ShoppingCartItemVO> cartItemVOList, List<ShoppingCartItem> newBeeMallShoppingCartItems) {
        if (!CollectionUtils.isEmpty(newBeeMallShoppingCartItems)) {
            //查询商品信息并做数据转换
            // 把购物车 中的items 的商品id 都拿出来放在一个集合中
            List<Long> goodsIds = newBeeMallShoppingCartItems.stream().map(ShoppingCartItem::getGoodsId).collect(Collectors.toList());
            //查询这些商品的详细信息
            List<GoodsInfo> goodsList = goodsInfoMapper.selectByPrimaryKeys(goodsIds);
            Map<Long, GoodsInfo> goodsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(goodsList)) {
                goodsList.forEach(goodInfo ->{
                    goodsMap.put(goodInfo.getGoodsId(), goodInfo);
                });
//                newBeeMallGoodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(MallGoodsInfo::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (ShoppingCartItem cartItem : newBeeMallShoppingCartItems) {
                ShoppingCartItemVO cartItemVO = new ShoppingCartItemVO();
                BeanUtil.copyProperties(cartItem, cartItemVO);
                if (goodsMap.containsKey(cartItem.getGoodsId())) {
                    GoodsInfo goodsTemp = goodsMap.get(cartItem.getGoodsId());
                    cartItemVO.setGoodsCoverImg(goodsTemp.getGoodsCoverImg());
                    String goodsName = goodsTemp.getGoodsName();
//                     字符串过长导致文字超出的问题
                    if (goodsName.length() > 28) {
                        goodsName = goodsName.substring(0, 28) + "...";
                    }
                    cartItemVO.setGoodsName(goodsName);
                    cartItemVO.setSellingPrice(goodsTemp.getSellingPrice());
                    cartItemVOList.add(cartItemVO);
                }
            }
        }
        return cartItemVOList;
    }

}


