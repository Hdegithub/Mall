package com.geekaca.mall.mapper;

import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.domain.ShoppingCartItem;
import com.geekaca.mall.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author kanata
* @description 针对表【tb_newbee_mall_shopping_cart_item】的数据库操作Mapper
* @createDate 2023-10-18 02:44:05
* @Entity com.geekaca.mall.domain.ShoppingCartItem
*/
@Mapper
public interface ShoppingCartItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCartItem record);

    int insertSelective(ShoppingCartItem record);

    ShoppingCartItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCartItem record);

    int updateByPrimaryKey(ShoppingCartItem record);

    List<ShoppingCartItemVO> getMyShoppingCartItems(PageQueryUtil pageUtil);

    int getTotalMyShoppingCartItems(PageQueryUtil pageUtil);


    List<ShoppingCartItemVO> selectByUserId(@Param("userId") Long userId, @Param("number") int number);
}
