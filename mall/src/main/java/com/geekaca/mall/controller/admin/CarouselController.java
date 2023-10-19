package com.geekaca.mall.controller.admin;

import com.geekaca.mall.controller.admin.param.BatchIdParam;
import com.geekaca.mall.controller.admin.param.CarouselParam;
import com.geekaca.mall.service.CarouselService;
import com.geekaca.mall.utils.PageResult;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/manage-api/v1")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;


    /**
     * 列表
     */
    @ApiOperation(value = "轮播图列表", notes = "轮播图列表")
    @RequestMapping(value = "/carousels", method = RequestMethod.GET)
    public Result list(@RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                       @RequestParam(required = false) @ApiParam(value = "每页条数") Integer pageSize) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageResult pageResult = carouselService.findCarousels(pageNumber, pageSize);

        Result result = ResultGenerator.genSuccessResult();

        result.setData(pageResult);

        return result;
    }

    /**
     * 添加
     */
    @ApiOperation(value = "新增轮播图", notes = "新增轮播图")
    @RequestMapping(value = "/carousels", method = RequestMethod.POST)
    public Result save(@RequestBody @Valid CarouselParam carouselParam) {
        int isOk = carouselService.saveCarousel(carouselParam);
        if (isOk != 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除轮播图", notes = "批量删除轮播图")
    @RequestMapping(value = "/carousels", method = RequestMethod.DELETE)
    public Result delete(@RequestBody BatchIdParam batchIdParam) {
        if (batchIdParam == null || batchIdParam.getIds().length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        int isOk = carouselService.deleteCarousels(batchIdParam.getIds());
        if (isOk != 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
