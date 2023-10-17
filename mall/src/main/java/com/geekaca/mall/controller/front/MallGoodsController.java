package com.geekaca.mall.controller.front;

import com.geekaca.mall.common.Constants;
import com.geekaca.mall.common.NewBeeMallException;
import com.geekaca.mall.service.GoodsInfoService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map params = new HashMap();
        if (goodsCategoryId == null && !StringUtils.hasText(keyword)) {
            NewBeeMallException.fail("搜索参数错误");
        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("goodsCategoryId", goodsCategoryId);
        params.put("page", pageNumber);
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        params.put("keyword", keyword);
        params.put("orderBy", orderBy);
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult pageResult = goodsInfoService.searchGoods(pageUtil);
        return ResultGenerator.genSuccessResult(pageResult);
    }
}
