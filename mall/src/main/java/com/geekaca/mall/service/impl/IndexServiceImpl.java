package com.geekaca.mall.service.impl;

import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.controller.vo.HotGoodsesVO;
import com.geekaca.mall.mapper.CarouselMapper;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<CarouselVO> getCarousels(Integer count) {
        return carouselMapper.getCarousels(count);
    }

    @Override
    public List<HotGoodsesVO> getHotGoods() {
        return goodsInfoMapper.findHotGoodsList();
    }
}
