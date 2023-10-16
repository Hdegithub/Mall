package com.geekaca.mall.controller.front;

import com.geekaca.mall.service.GoodsInfoService;
import com.geekaca.mall.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//api/v1
@RestController
@RequestMapping("/api/v1")
public class MallGoodsController {
    @Autowired
    private GoodsInfoService goodsInfoService;

    @GetMapping("/goods/detail/{goodsId}")
    public Result getGoodsById(@PathVariable("goodsId") Long goodsId) {


        return null;
    }

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口", notes = "根据关键字和分类id进行搜索")
    public Result search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyword,
                                                                    @RequestParam(required = false) @ApiParam(value = "分类id") Long goodsCategoryId,
                                                                    @RequestParam(required = false) @ApiParam(value = "排序关键字") String orderBy,
                                                                    @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber
    ) {

        return null;
    }
}
