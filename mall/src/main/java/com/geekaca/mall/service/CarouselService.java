package com.geekaca.mall.service;

import com.geekaca.mall.controller.vo.CarouselVO;
import java.util.List;

public interface CarouselService {
    List<CarouselVO> getCarousels(Integer count);
}
