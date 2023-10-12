package com.geekaca.mall.controller.admin;

import com.geekaca.mall.domain.GoodsCategory;
import com.geekaca.mall.service.AdminUserService;
import com.geekaca.mall.service.GoodsCategoryService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage-api/v1")
public class CategoryController {
    @Resource
    private GoodsCategoryService categoryService;

    @ApiOperation(value = "商品分类列表" ,notes = "查询所有商品分类")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public Result list(@RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                       @RequestParam(required = false) @ApiParam(value = "每页条数") Integer pageSize,
                       @RequestParam Map<String, Object> params){
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getAllGoodsCategories(pageQueryUtil));
    }
}
