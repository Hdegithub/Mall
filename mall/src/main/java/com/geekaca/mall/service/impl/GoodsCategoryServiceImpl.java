package com.geekaca.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.geekaca.mall.common.Constants;
import com.geekaca.mall.common.MallCategoryLevelEnum;
import com.geekaca.mall.controller.admin.param.CategoryParam;
import com.geekaca.mall.controller.vo.MallIndexCategoryVO;
import com.geekaca.mall.controller.vo.SecondLevelCategoryVO;
import com.geekaca.mall.controller.vo.ThirdLevelCategoryVO;
import com.geekaca.mall.domain.GoodsCategory;
import com.geekaca.mall.mapper.GoodsCategoryMapper;
import com.geekaca.mall.service.GoodsCategoryService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
        List<MallIndexCategoryVO> newBeeMallIndexCategoryVOS = new ArrayList<>();
        //获取一级分类的固定数量的数据
        List<GoodsCategory> firstLevelCategories = categoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), MallCategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
            //获取二级分类的数据
            List<GoodsCategory> secondLevelCategories = categoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, MallCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                List<Long> secondLevelCategoryIds = secondLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
                //获取三级分类的数据
                List<GoodsCategory> thirdLevelCategories = categoryMapper.selectByLevelAndParentIdsAndNumber(secondLevelCategoryIds, MallCategoryLevelEnum.LEVEL_THREE.getLevel(), 0);
                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
                    //根据 parentId 将 thirdLevelCategories 分组
                    Map<Long, List<GoodsCategory>> thirdLevelCategoryMap = thirdLevelCategories.stream().collect(groupingBy(GoodsCategory::getParentId));
                    List<SecondLevelCategoryVO> secondLevelCategoryVOS = new ArrayList<>();
                    //处理二级分类
                    for (GoodsCategory secondLevelCategory : secondLevelCategories) {
                        SecondLevelCategoryVO secondLevelCategoryVO = new SecondLevelCategoryVO();
                        BeanUtil.copyProperties(secondLevelCategory, secondLevelCategoryVO);
                        //如果该二级分类下有数据则放入 secondLevelCategoryVOS 对象中
                        if (thirdLevelCategoryMap.containsKey(secondLevelCategory.getCategoryId())) {
                            //根据二级分类的id取出thirdLevelCategoryMap分组中的三级分类list
                            List<GoodsCategory> tempGoodsCategories = thirdLevelCategoryMap.get(secondLevelCategory.getCategoryId());
                            secondLevelCategoryVO.setThirdLevelCategoryVOS((BeanUtil.copyToList(tempGoodsCategories, ThirdLevelCategoryVO.class)));
                            secondLevelCategoryVOS.add(secondLevelCategoryVO);
                        }
                    }
                    //处理一级分类
                    if (!CollectionUtils.isEmpty(secondLevelCategoryVOS)) {
                        //根据 parentId 将 thirdLevelCategories 分组
                        Map<Long, List<SecondLevelCategoryVO>> secondLevelCategoryVOMap = secondLevelCategoryVOS.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
                        for (GoodsCategory firstCategory : firstLevelCategories) {
                            MallIndexCategoryVO newBeeMallIndexCategoryVO = new MallIndexCategoryVO();
                            BeanUtil.copyProperties(firstCategory, newBeeMallIndexCategoryVO);
                            //如果该一级分类下有数据则放入 newBeeMallIndexCategoryVOS 对象中
                            if (secondLevelCategoryVOMap.containsKey(firstCategory.getCategoryId())) {
                                //根据一级分类的id取出secondLevelCategoryVOMap分组中的二级级分类list
                                List<SecondLevelCategoryVO> tempGoodsCategories = secondLevelCategoryVOMap.get(firstCategory.getCategoryId());
                                newBeeMallIndexCategoryVO.setSecondLevelCategoryVOS(tempGoodsCategories);
                                newBeeMallIndexCategoryVOS.add(newBeeMallIndexCategoryVO);
                            }
                        }
                    }
                }
            }
            return newBeeMallIndexCategoryVOS;
        } else {
            return null;
        }
    }

    @Override
    public PageResult getCategoryPage(PageQueryUtil pageQueryUtil) {
        //需要一个能够 根据分页参数 查询的sql
        List<GoodsCategory> categoryList = categoryMapper.getCategoryList(pageQueryUtil);
        //查询符合条件的总记录条数
        int totalCategoryCounts = categoryMapper.getTotalCategoryCounts(pageQueryUtil);
        PageResult pageResult = new PageResult(categoryList, totalCategoryCounts, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public GoodsCategory getCategoryByid(Long categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
