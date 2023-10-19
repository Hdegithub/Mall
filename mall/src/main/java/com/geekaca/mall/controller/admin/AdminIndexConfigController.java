package com.geekaca.mall.controller.admin;

import com.geekaca.mall.common.IndexConfigTypeEnum;
import com.geekaca.mall.config.annotation.TokenToAdminUser;
import com.geekaca.mall.domain.UserToken;
import com.geekaca.mall.service.IndexService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Api(value = "v1", tags = "8-4.后台管理系统首页配置模块接口")
@RequestMapping("/manage-api/v1")
public class AdminIndexConfigController {
    @Autowired
    private IndexService indexService;

    /**
     * 列表
     */
    @RequestMapping(value = "/indexConfigs", method = RequestMethod.GET)
    @ApiOperation(value = "首页配置列表", notes = "首页配置列表")
    public Result list(@RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                       @RequestParam(required = false) @ApiParam(value = "每页条数") Integer pageSize,
                       @RequestParam(required = false) @ApiParam(value = "1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐") Integer configType,
                       @RequestParam Map<String, Object> params) {
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        params.put("configType", configType);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(indexService.getConfigsPage(pageUtil));
    }
}
