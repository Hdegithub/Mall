package com.geekaca.mall.controller.front;

import com.geekaca.mall.common.Constants;
import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.controller.vo.IndexInfoVO;
import com.geekaca.mall.service.CarouselService;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @RequestMapping("/index-infos")
    public Result<IndexInfoVO> indexInfo(){
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        List<CarouselVO> mallcarousels = carouselService.getCarousels(Constants.INDEX_CAROUSEL_NUMBER);
        indexInfoVO.setCarousels(mallcarousels);
        return ResultGenerator.genSuccessResult(indexInfoVO);
    }

}
