package com.geekaca.mall.mapper;

import com.geekaca.mall.controller.vo.OrderDetailVO;
import com.geekaca.mall.controller.vo.OrderItemVO;
import com.geekaca.mall.domain.Order;
import com.geekaca.mall.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 29484
 * @description 针对表【tb_newbee_mall_order】的数据库操作Mapper
 * @createDate 2023-10-23 15:37:59
 * @Entity com.geekaca.mall.domain.Order
 */
@Mapper
public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findOrderList(PageQueryUtil pageUtil);

    int getTotalOrders(PageQueryUtil pageUtil);

    //配货
    int checkDone(@Param("orderIds") List<Long> asList);

    //出库
    int checkOut(@Param("orderIds") List<Long> orderIds);

    //查询订单状态
    List<Order> selectByPrimaryKeys(@Param("orderIds") List<Long> orderIds);

    //关闭订单
    int closeOrder(@Param("orderIds") List<Long> orderIds, @Param("orderStatus") int orderStatus);

}
