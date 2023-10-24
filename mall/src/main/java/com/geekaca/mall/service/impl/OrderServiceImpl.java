package com.geekaca.mall.service.impl;


import com.geekaca.mall.common.*;
import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.domain.*;
import com.geekaca.mall.mapper.*;
import com.geekaca.mall.utils.BeanUtil;
import com.geekaca.mall.utils.NumberUtil;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements com.geekaca.mall.service.OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private ShoppingCartItemMapper cartItemMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public PageResult getOrdersPage(PageQueryUtil pageUtil) {
        List<Order> orderList = orderMapper.findOrderList(pageUtil);
        int totalOrders = orderMapper.getTotalOrders(pageUtil);
        PageResult pageResult = new PageResult(orderList, totalOrders, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveOrder(Long userId, UserAddress address, List<ShoppingCartItemVO> itemsForSave) {
        //拿到 购物车item 的ids
        List<Long> itemIdList = itemsForSave.stream().map(ShoppingCartItemVO::getCartItemId).collect(Collectors.toList());
        //商品ids
        List<Long> goodsIds = itemsForSave.stream().map(ShoppingCartItemVO::getGoodsId).collect(Collectors.toList());
        //商品信息
        List<GoodsInfo> newBeeMallGoods = goodsInfoMapper.selectByPrimaryKeys(goodsIds);
        //检查是否包含已下架商品
        List<GoodsInfo> goodsListNotSelling = newBeeMallGoods.stream()
                .filter(newBeeMallGoodsTemp -> newBeeMallGoodsTemp.getGoodsSellStatus() != Constants.SELL_STATUS_UP)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsListNotSelling)) {
            //goodsListNotSelling 对象非空则表示有下架商品
            NewBeeMallException.fail(goodsListNotSelling.get(0).getGoodsName() + "已下架，无法生成订单");
        }
        Map<Long, GoodsInfo> newBeeMallGoodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(GoodsInfo::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
        //判断商品库存
        for (ShoppingCartItemVO shoppingCartItemVO : itemsForSave) {
            //查出的商品中不存在购物车中的这条关联商品数据，直接返回错误提醒
            if (!newBeeMallGoodsMap.containsKey(shoppingCartItemVO.getGoodsId())) {
                NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
            }
            //存在数量大于库存的情况，直接返回错误提醒
            if (shoppingCartItemVO.getGoodsCount() > newBeeMallGoodsMap.get(shoppingCartItemVO.getGoodsId()).getStockNum()) {
                NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
            }
        }
        //删除购物项
        if (!CollectionUtils.isEmpty(itemIdList) && !CollectionUtils.isEmpty(goodsIds) && !CollectionUtils.isEmpty(newBeeMallGoods)) {
            if (cartItemMapper.deleteBatch(itemIdList) > 0) {
                List<StockNumDTO> stockNumDTOS = BeanUtil.copyList(itemsForSave, StockNumDTO.class);
                int updateStockNumResult = goodsInfoMapper.updateStockNum(stockNumDTOS);
                if (updateStockNumResult < 1) {
                    NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
                }
                //生成订单号
                String orderNo = NumberUtil.genOrderNo();
                int priceTotal = 0;
                //保存订单
                Order newBeeMallOrder = new Order();
                newBeeMallOrder.setOrderNo(orderNo);
                newBeeMallOrder.setUserId(userId);
                //总价
                for (ShoppingCartItemVO newBeeMallShoppingCartItemVO : itemsForSave) {
                    priceTotal += newBeeMallShoppingCartItemVO.getGoodsCount() * newBeeMallShoppingCartItemVO.getSellingPrice();
                }
                if (priceTotal < 1) {
                    NewBeeMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                newBeeMallOrder.setTotalPrice(priceTotal);
                String extraInfo = "";
                newBeeMallOrder.setExtraInfo(extraInfo);
                //生成订单项并保存订单项纪录
                if (orderMapper.insertSelective(newBeeMallOrder) > 0) {
                    //生成订单收货地址快照，并保存至数据库
                    OrderAddress orderAddress = new OrderAddress();
                    BeanUtil.copyProperties(address, orderAddress);
                    orderAddress.setOrderId(newBeeMallOrder.getOrderId());
                    //生成所有的订单项快照，并保存至数据库
                    List<OrderItem> orderItems = new ArrayList<>();
                    for (ShoppingCartItemVO newBeeMallShoppingCartItemVO : itemsForSave) {
                        OrderItem newBeeMallOrderItem = new OrderItem();
                        //使用BeanUtil工具类将newBeeMallShoppingCartItemVO中的属性复制到newBeeMallOrderItem对象中
                        BeanUtil.copyProperties(newBeeMallShoppingCartItemVO, newBeeMallOrderItem);
                        //NewBeeMallOrderMapper文件insert()方法中使用了useGeneratedKeys因此orderId可以获取到
                        newBeeMallOrderItem.setOrderId(newBeeMallOrder.getOrderId());
                        orderItems.add(newBeeMallOrderItem);
                    }
                    //保存至数据库
                    if (orderItemMapper.insertBatch(orderItems) > 0 && orderAddressMapper.insertSelective(orderAddress) > 0) {
                        //所有操作成功后，将订单号返回，以供Controller方法跳转到订单详情
                        return orderNo;
                    }
                    NewBeeMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                NewBeeMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
            }
            NewBeeMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
        }
        NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
        return ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult();
    }

    @Override
    public String paySuccess(String orderNo, int payType) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order != null) {
            //订单状态判断 非待支付状态下不进行修改操作
            if (order.getOrderStatus().intValue() != NewBeeMallOrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
                return ServiceResultEnum.ORDER_STATUS_ERROR.getResult();
            }
            order.setOrderStatus(NewBeeMallOrderStatusEnum.ORDER_PAID.getOrderStatus());
            order.setPayType(payType);
            order.setPayStatus(PayTypeEnum.PAY_SUCCESS.getPayStatus());
            order.setPayTime(new Date());
            order.setUpdateTime(new Date());
            if (orderMapper.updateByPrimaryKeySelective(order) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }


}
