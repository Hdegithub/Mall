package com.geekaca.mall.mapper;

import com.geekaca.mall.controller.vo.OrderDetailVO;
import com.geekaca.mall.controller.vo.OrderItemVO;
import com.geekaca.mall.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 29484
* @description 针对表【tb_newbee_mall_order_item】的数据库操作Mapper
* @createDate 2023-10-24 00:32:39
* @Entity com.geekaca.mall.domain.OrderItem
*/
@Mapper
public interface OrderItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> selectByOrderId(Long orderId);

    int insertBatch(@Param("orderItems") List<OrderItem> orderItems);
}
