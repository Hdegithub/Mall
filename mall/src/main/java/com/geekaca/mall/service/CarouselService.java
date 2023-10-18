package com.geekaca.mall.service;


import com.geekaca.mall.utils.PageResult;

public interface CarouselService {
    PageResult findCarousels(Integer pageNo, Integer pageSize);
}
