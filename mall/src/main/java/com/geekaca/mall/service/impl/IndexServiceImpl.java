package com.geekaca.mall.service.impl;

import com.geekaca.mall.controller.admin.param.IndexConfigAddParam;
import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.controller.vo.HotGoodsesVO;
import com.geekaca.mall.domain.GoodsCategory;
import com.geekaca.mall.domain.IndexConfig;
import com.geekaca.mall.mapper.CarouselMapper;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.mapper.IndexConfigMapper;
import com.geekaca.mall.service.IndexService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Override
    public List<CarouselVO> getCarousels(Integer count) {
        return carouselMapper.getCarousels(count);
    }

    @Override
    public List<HotGoodsesVO> getHotGoods() {
        return goodsInfoMapper.findHotGoodsList();
    }

    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<IndexConfig> configList = indexConfigMapper.findIndexConfigList(pageUtil);
        int indexConfigsCount = indexConfigMapper.getIndexConfigsCount(pageUtil);
        PageResult pageResult = new PageResult(configList, indexConfigsCount, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean saveIndexConfig(IndexConfig indexConfig) {
        indexConfig.setConfigName(indexConfig.getConfigName());
        indexConfig.setRedirectUrl(indexConfig.getRedirectUrl());
        indexConfig.setConfigRank(indexConfig.getConfigRank());
        indexConfig.setConfigType(indexConfig.getConfigType());
        int insert = indexConfigMapper.insertSelective(indexConfig);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteIndex(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return indexConfigMapper.deleteByIds(ids) > 0;
    }
}
