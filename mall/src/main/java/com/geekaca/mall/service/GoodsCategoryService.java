package com.geekaca.mall.service;

import com.geekaca.mall.domain.GoodsCategory;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

import java.util.List;

public interface GoodsCategoryService {
    /**
     * 查询所有类别
     * @return
     */
    PageResult getAllGoodsCategories(PageQueryUtil pageUtil);
}
