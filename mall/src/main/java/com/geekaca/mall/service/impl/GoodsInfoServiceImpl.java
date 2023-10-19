package com.geekaca.mall.service.impl;


import com.geekaca.mall.common.NewBeeMallException;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.controller.admin.param.GoodsAddParam;
import com.geekaca.mall.controller.admin.param.GoodsUpdateParam;
import com.geekaca.mall.controller.vo.FrontPageVo;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.service.GoodsInfoService;
import com.geekaca.mall.utils.PageQueryUtil;
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
        List<GoodsInfo> goodsList = goodsInfoMapper.selectPageByName((pageNo - 1), pageSize, goodsName);
        int goodsCount = goodsInfoMapper.findGoodsCount(goodsName);
        PageResult pageResult = new PageResult(goodsList, goodsCount, pageSize, pageNo);
        return pageResult;
    }

    @Override
    public Boolean addGood(GoodsAddParam goodsAddParam) {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsCategoryId(goodsAddParam.getGoodsCategoryId());
        goodsInfo.setGoodsName(goodsAddParam.getGoodsName());
        goodsInfo.setGoodsIntro(goodsAddParam.getGoodsIntro());
        goodsInfo.setOriginalPrice(goodsAddParam.getOriginalPrice());
        goodsInfo.setSellingPrice(goodsAddParam.getSellingPrice());
        goodsInfo.setStockNum(goodsAddParam.getStockNum());
        goodsInfo.setTag(goodsAddParam.getTag());
        goodsInfo.setGoodsSellStatus(goodsAddParam.getGoodsSellStatus());
        goodsInfo.setGoodsCoverImg(goodsAddParam.getGoodsCoverImg());
        goodsInfo.setGoodsDetailContent(goodsAddParam.getGoodsDetailContent());

        int addGoods = goodsInfoMapper.addGoods(goodsInfo);
        if (addGoods > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateGoods(GoodsUpdateParam goodsUpdateParam) {
        //GoodsUpdateParam转换 为   GoodsInfo 属性值和数据库对应
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsId(goodsUpdateParam.getGoodsId());
        goodsInfo.setGoodsCategoryId(goodsUpdateParam.getGoodsCategoryId());
        goodsInfo.setGoodsName(goodsUpdateParam.getGoodsName());
        goodsInfo.setGoodsIntro(goodsUpdateParam.getGoodsIntro());
        goodsInfo.setOriginalPrice(goodsUpdateParam.getOriginalPrice());
        goodsInfo.setSellingPrice(goodsUpdateParam.getSellingPrice());
        goodsInfo.setStockNum(goodsUpdateParam.getStockNum());
        goodsInfo.setTag(goodsUpdateParam.getTag());
        goodsInfo.setGoodsSellStatus(goodsUpdateParam.getGoodsSellStatus());
        goodsInfo.setGoodsCoverImg(goodsUpdateParam.getGoodsCoverImg());
        goodsInfo.setGoodsDetailContent(goodsUpdateParam.getGoodsDetailContent());
        int updateGoods = goodsInfoMapper.updateGoods(goodsInfo);
        if (updateGoods > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateSellStatus(Long[] ids, int sellStatus) {
        return goodsInfoMapper.updateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchGoods(PageQueryUtil pageUtil) {
        List<GoodsInfo> goodsList = goodsInfoMapper.findGoodsListBySearch(pageUtil);
        int total = goodsInfoMapper.getTotalGoodsBySearch(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());

        return pageResult;
    }

    @Override
    public GoodsInfo getGoodsById(Long goodsId) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
        if (goodsInfo == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        return goodsInfo;
    }
}

