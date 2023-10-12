package com.geekaca.mall.controller.admin;

import com.geekaca.mall.controller.admin.param.AdminLoginParam;
import com.geekaca.mall.service.AdminUserService;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 后台用户管理接口
 * v1 版本号，可以给接口通过路径区分，设置版本号
 */
@RestController
@RequestMapping("/manage-api/v1")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    /**
     * 当你想把用户提交的参数JSON 转变为一个Java 对象接收，需要 @RequestBody
     *
     * @Valid  为了让这个类对象能够进行参数校验
     *
     * @param adminLoginParam
     * @return
     */
    @RequestMapping("/adminUser/login")
    public Result login(@Valid @RequestBody AdminLoginParam adminLoginParam){
        String loginToken = adminUserService.login(adminLoginParam);
        if (loginToken == null){
            //登陆失败
            return ResultGenerator.genFailResult("登陆失败");
        }else{
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginToken);
            return result;
        }
    }
}
