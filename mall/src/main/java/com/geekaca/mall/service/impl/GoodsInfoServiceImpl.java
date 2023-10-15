package com.geekaca.mall.service.impl;

import cn.hutool.db.Page;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.service.GoodsInfoService;
import com.geekaca.mall.utils.PageResult;
import com.geekaca.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public PageResult findAllGoods(Integer pageNo, Integer pageSize) {
        List<GoodsInfo> goodsList = goodsInfoMapper.findGoodsList((pageNo - 1) * pageSize, pageSize);
        int goodsCount = goodsInfoMapper.findGoodsCount();
        PageResult pageResult = new PageResult(goodsList, goodsCount, pageSize, pageNo);
        return pageResult;
    }
<<<<<<< HEAD

    @Override
    public String addGoods(GoodsInfo goodsInfo) {
        //向数据库插入商品数据
        int addgoods = goodsInfoMapper.addgoods(goodsInfo);
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectgoodsall();
        if (addgoods > 0) {
            for (int i = 0; i < goodsInfoList.size(); i++) {
                goodsInfoList.add(goodsInfo);
            }
        }else {
            return "添加失败";
        }
        return "添加成功";
    }
=======
>>>>>>> parent of 31f077c... 新增商品
}
