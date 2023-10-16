package com.geekaca.mall.service;

import com.geekaca.mall.utils.PageResult;
import org.apache.ibatis.annotations.Param;

public interface GoodsInfoService {
    PageResult findAllGoods(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("goodsName") String goodsName);
}
