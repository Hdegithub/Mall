package com.geekaca.mall.service;

import com.geekaca.mall.controller.admin.param.CategoryParam;
import com.geekaca.mall.controller.vo.MallIndexCategoryVO;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;

import java.util.List;

public interface GoodsCategoryService {
    /**
     * 查询所有类别
     * @return
     */
    PageResult getAllGoodsCategories(PageQueryUtil pageUtil);

    /**
     * 新增商品类别
     * @param categoryName 分类名称
     * @param categoryRank 排序值
     * @return
     */
    Boolean saveCategory(String categoryName, Integer categoryRank);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteCategory(Long[] ids);

    Boolean saveCategory(CategoryParam categoryParam);

    Boolean updateCategory(CategoryParam categoryParam);

    List<MallIndexCategoryVO> getCategoriesForIndex();

    PageResult getCategoryPage(PageQueryUtil pageQueryUtil);
}
