package com.geekaca.mall.controller.front;

import com.geekaca.mall.common.Constants;
import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.controller.vo.HotGoodsesVO;
import com.geekaca.mall.controller.vo.IndexInfoVO;
import com.geekaca.mall.service.IndexService;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class IndexlController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/index-infos")
    public Result<IndexInfoVO> indexInfo(){
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        List<CarouselVO> mallcarousels = indexService.getCarousels(Constants.INDEX_CAROUSEL_NUMBER);
        indexInfoVO.setCarousels(mallcarousels);
        List<HotGoodsesVO> hotGoods  = indexService.getHotGoods();
        indexInfoVO.setHotGoodses(hotGoods);
        return ResultGenerator.genSuccessResult(indexInfoVO);
    }
    @ApiOperation(value = "获取首页热门商品", notes = "")
    @RequestMapping(value = "/getHotGoodsesList", method = RequestMethod.GET)
    public Result getHotGoodsesList(){
        List<HotGoodsesVO> HotGoodses = indexService.getHotGoods();
        Result result = ResultGenerator.genSuccessResult();
        result.setData(HotGoodses);
        return result;
    }

}
