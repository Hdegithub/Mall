package com.geekaca.mall.service.impl;


import com.geekaca.mall.controller.admin.param.GoodsAddParam;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.service.GoodsInfoService;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public PageResult findAllGoods(Integer pageNo, Integer pageSize, String goodsName) {
        List<GoodsInfo> goodsList = goodsInfoMapper.selectPageByName(pageNo, pageSize, goodsName);
        int goodsCount = goodsInfoMapper.findGoodsCount(goodsName);
        PageResult pageResult = new PageResult(goodsList, goodsCount, pageSize, pageNo);
        return pageResult;
    }

    @Override
    public Boolean addGood(GoodsAddParam goodsAddParam) {
        GoodsInfo goodsInfo = new GoodsInfo();
        int addGoods = goodsInfoMapper.addGoods(goodsInfo);
        if (addGoods > 0) {
            goodsInfo.setGoodsName(goodsAddParam.getGoodsName());
            goodsInfo.setGoodsIntro(goodsAddParam.getGoodsIntro());
            goodsInfo.setOriginalPrice(goodsAddParam.getOriginalPrice());
            goodsInfo.setSellingPrice(goodsAddParam.getSellingPrice());
            goodsInfo.setStockNum(goodsAddParam.getStockNum());
            goodsInfo.setTag(goodsAddParam.getTag());
            goodsInfo.setGoodsSellStatus(goodsAddParam.getGoodsSellStatus());
            goodsInfo.setGoodsCoverImg(goodsAddParam.getGoodsCoverImg());
            goodsInfo.setGoodsDetailContent(goodsAddParam.getGoodsDetailContent());
            return true;
        } else {
            return false;
        }
    }
}
