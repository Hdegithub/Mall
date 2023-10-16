package com.geekaca.mall.controller.admin;


import cn.hutool.core.bean.BeanUtil;
import com.geekaca.mall.controller.admin.param.GoodsAddParam;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.service.GoodsInfoService;
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
public class GoodsController {

    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * test
     *
     * @param pageNumber
     * @param pageSize
     * @param goodsName       模糊搜索
     * @param goodsSellStatus
     * @return
     */
    @ApiOperation(value = "商品列表", notes = "查询所有商品")
    @RequestMapping(value = "/goods/list", method = RequestMethod.GET)
    public Result list(@RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                       @RequestParam(required = false) @ApiParam(value = "每页条数") Integer pageSize,
                       @RequestParam(required = false) @ApiParam(value = "商品名称") String goodsName,
                       @RequestParam(required = false) @ApiParam(value = "上架状态 0-上架 1-下架") Integer goodsSellStatus) {

        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageResult pageResult = goodsInfoService.findAllGoods(pageNumber, pageSize ,goodsName);
        //还需要一个查询，查询符合条件的记录条数
        Result result = ResultGenerator.genSuccessResult();
        result.setData(pageResult);
        return result;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    @ApiOperation(value = "新增商品信息", notes = "新增商品信息")
    public Result save(@RequestBody @Valid GoodsAddParam goodsAddParam) {
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtil.copyProperties(goodsAddParam, goodsInfo);
        return ResultGenerator.genSuccessResult(goodsInfoService.addGoods(goodsInfo));
    }

    @RequestMapping(value = "/selectbyIdandName", method = RequestMethod.GET)
    @ApiOperation(value = "模糊查询", notes = "模糊查询")
    public GoodsInfo getById(@RequestParam(required = true) String name, @RequestParam(required = false) Long goodsCategoryid) {
        return goodsInfoService.getgoodsByIdname(name, goodsCategoryid);
    }
}
