package com.geekaca.mall.mapper;

import com.geekaca.mall.domain.GoodsInfo;

/**
* @author magol
* @description 针对表【tb_newbee_mall_goods_info】的数据库操作Mapper
* @createDate 2023-10-13 10:26:25
* @Entity com.geekaca.mall.domain.GoodsInfo
*/
public interface GoodsInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

}
