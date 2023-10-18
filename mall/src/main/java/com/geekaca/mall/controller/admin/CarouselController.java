package com.geekaca.mall.controller.admin;

import com.geekaca.mall.service.CarouselService;
import com.geekaca.mall.utils.PageResult;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/manage-api/v1")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value = "轮播图列表", notes = "轮播图列表")
    @RequestMapping(value = "/carousels", method = RequestMethod.GET)
    public Result list(@RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                       @RequestParam(required = false) @ApiParam(value = "每页条数") Integer pageSize) {
        if (pageNumber == null){
            pageNumber = 1;
        }
        if (pageSize == null){
            pageSize = 20;
        }
        PageResult pageResult = carouselService.findCarousels(pageNumber, pageSize);

        Result result = ResultGenerator.genSuccessResult();

        result.setData(pageResult);

        return result;
    }
}
