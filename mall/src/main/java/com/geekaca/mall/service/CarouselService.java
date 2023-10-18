package com.geekaca.mall.service;


import com.geekaca.mall.controller.admin.param.CarouselParam;
import com.geekaca.mall.utils.PageResult;

public interface CarouselService {
    PageResult findCarousels(Integer pageNo, Integer pageSize);

    int saveCarousel(CarouselParam carouselParam);

    int deleteCarousels(Long[] ids);
}
