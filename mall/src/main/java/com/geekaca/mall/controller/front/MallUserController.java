package com.geekaca.mall.controller.front;


import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.service.MallUserService;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MallUserController {
    @Autowired
    private MallUserService mallUserService;

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册", notes = "")
    public Result register(@RequestBody @Valid MallUserRegisterParam mallUserRegisterParam) {
        boolean isRegOk = mallUserService.register(mallUserRegisterParam);

        log.info("register api,loginName={},loginResult={}", mallUserRegisterParam.getLoginName(), isRegOk);

        //注册成功
        if (isRegOk) {
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult("注册失败");
    }
}
