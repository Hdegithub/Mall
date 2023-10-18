package com.geekaca.mall.service;

import com.geekaca.mall.controller.admin.param.CategoryParam;
import com.geekaca.mall.controller.admin.param.GoodsAddParam;

import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.controller.vo.FrontPageVo;
import com.geekaca.mall.utils.PageResult;
import org.apache.ibatis.annotations.Param;

public interface GoodsInfoService {
    PageResult findAllGoods(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("goodsName") String goodsName);

    Boolean addGood(GoodsAddParam goodsAddParam);



    boolean updateSellStatus(Long[] ids,int sellStatus);

    PageResult searchGoods(PageQueryUtil pageUtil);

    GoodsInfo getGoodsById(Long goodsId);
//    PageResult searchFrontGoods(FrontPageVo frontPageVo);
}
