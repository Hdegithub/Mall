package com.geekaca.mall.service.impl;


import com.geekaca.mall.domain.Carousel;
import com.geekaca.mall.mapper.CarouselMapper;
import com.geekaca.mall.service.CarouselService;
import com.geekaca.mall.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public PageResult findCarousels(Integer pageNo, Integer pageSize) {
        List<Carousel> carouselList = carouselMapper.findCarouselList((pageNo - 1) * pageSize, pageSize);
        int carouselCount = carouselMapper.findCarouselCount();
        PageResult pageResult = new PageResult(carouselList, carouselCount, pageSize, pageNo);
        return pageResult;
    }
}
