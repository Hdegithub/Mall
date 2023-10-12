package com.geekaca.mall.service;

import com.geekaca.mall.domain.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {
    /**
     * 查询所有类别
     * @return
     */
    List<GoodsCategory> getAllGoodsCategories();
}
