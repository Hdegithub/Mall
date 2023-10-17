package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.controller.vo.HotGoodsesVO;


import java.util.List;

public interface IndexService {
    List<CarouselVO> getCarousels(Integer count);

    List<HotGoodsesVO> getHotGoods();
}
