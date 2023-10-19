package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.controller.vo.HotGoodsesVO;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface IndexService {
    List<CarouselVO> getCarousels(Integer count);

    List<HotGoodsesVO> getHotGoods();

    PageResult getConfigsPage(PageQueryUtil pageUtil);
}
