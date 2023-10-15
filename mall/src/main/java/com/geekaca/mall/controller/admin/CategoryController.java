package com.geekaca.mall.controller.admin;


import com.geekaca.mall.controller.admin.param.BatchIdParam;
import com.geekaca.mall.controller.admin.param.CategoryParam;
import com.geekaca.mall.service.GoodsCategoryService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/manage-api/v1")
public class CategoryController {
    @Resource
    private GoodsCategoryService categoryService;

    /**
     * 分类列表
     */
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

    /**
     * 分类添加
     */
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public Result save(@RequestBody @Valid CategoryParam categoryParam) {
        if (categoryService.saveCategory(categoryParam)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    /**
     * 分类修改
     */
    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public Result update(@RequestBody @Valid CategoryParam categoryParam) {
        if (categoryService.updateCategory(categoryParam)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败！");
        }
    }

    /**
     * 分类删除
     */
    @RequestMapping(value = "/categories", method = RequestMethod.DELETE)
    public Result delete(@RequestBody BatchIdParam batchIdParam) {
        if (batchIdParam == null || batchIdParam.getIds().length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (categoryService.deleteCategory(batchIdParam.getIds())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }




}
