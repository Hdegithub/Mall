package com.geekaca.mall.service;

import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.utils.PageResult;

import java.util.List;

public interface GoodsInfoService {
    PageResult findAllGoods(Integer pageNo, Integer pageSize);
}
