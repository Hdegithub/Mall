package com.geekaca.mall.controller.front;


import com.geekaca.mall.common.NewBeeMallException;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.controller.vo.MallIndexCategoryVO;
import com.geekaca.mall.service.GoodsCategoryService;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Api(value = "v1", tags = "3.商城分类页面接口")
@Slf4j
public class MallCategoryController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/categories")
    @ApiOperation(value = "获取分类数据", notes = "分类页面使用")
    public Result getCategories() {
        List<MallIndexCategoryVO> categoriesForIndex = goodsCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categoriesForIndex)) {
            NewBeeMallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(categoriesForIndex);
    }
}
