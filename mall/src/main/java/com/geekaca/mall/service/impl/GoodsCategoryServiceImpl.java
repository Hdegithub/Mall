package com.geekaca.mall.service.impl;

import com.geekaca.mall.controller.admin.param.CategoryParam;
import com.geekaca.mall.controller.vo.MallIndexCategoryVO;
import com.geekaca.mall.domain.GoodsCategory;
import com.geekaca.mall.mapper.GoodsCategoryMapper;
import com.geekaca.mall.service.GoodsCategoryService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Override
    public PageResult getAllGoodsCategories(PageQueryUtil pageUtil) {
        //查询符合条件的所有 类别数据
        List<GoodsCategory> categoryList = categoryMapper.findAll(pageUtil);
        // 查询数量
        int total = categoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean saveCategory(String categoryName, Integer categoryRank) {
        GoodsCategory temp = categoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setCategoryName(categoryName);
            goodsCategory.setCategoryRank(categoryRank);
            return categoryMapper.insertSelective(goodsCategory) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteCategory(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除分类数据
        return categoryMapper.deleteByIds(ids) > 0;
    }

    @Override
    public Boolean saveCategory(CategoryParam categoryParam) {
        GoodsCategory temp = categoryMapper.selectByCategoryName(categoryParam.getCategoryName());
        if (temp == null) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setCategoryName(categoryParam.getCategoryName());
            goodsCategory.setCategoryRank(categoryParam.getCategoryRank());
            return categoryMapper.insertSelective(goodsCategory) > 0;
        }
        return false;
    }

    @Override
    public Boolean updateCategory(CategoryParam categoryParam) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setCategoryId(categoryParam.getCategoryId());
        goodsCategory.setCategoryName(categoryParam.getCategoryName());
        goodsCategory.setCategoryRank(categoryParam.getCategoryRank());
        goodsCategory.setUpdateTime(new Date());
        int i = categoryMapper.updateByPrimaryKeySelective(goodsCategory);
        return i > 0;
    }

    @Override
    public List<MallIndexCategoryVO> getCategoriesForIndex() {

        return null;
    }
}
