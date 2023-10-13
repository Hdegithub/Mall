package com.geekaca.mall.controller.admin;

import com.auth0.jwt.interfaces.Claim;
import com.geekaca.mall.controller.admin.param.AdminLoginParam;
import com.geekaca.mall.domain.AdminUser;
import com.geekaca.mall.service.AdminUserService;
import com.geekaca.mall.utils.JwtUtil;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

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
     * @param adminLoginParam
     * @return
     * @Valid 为了让这个类对象能够进行参数校验
     */
    @PostMapping("/adminUser/login")
    public Result login(@Valid @RequestBody AdminLoginParam adminLoginParam) {
        String loginToken = adminUserService.login(adminLoginParam);
        if (loginToken == null) {
            //登陆失败
            return ResultGenerator.genFailResult("登陆失败");
        } else {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginToken);
            return result;
        }
    }

    @GetMapping("/adminUser/profile")
    @ApiOperation(value = "获取管理员信息", notes = "获取管理员信息显示在前端界面")
    public Result profile(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        Claim userNameClaim = stringClaimMap.get("userName");
        String userName = userNameClaim.asString();
        //todo: 用userId 去数据库中查 用户的信息
        AdminUser user = new AdminUser();
        Result result = ResultGenerator.genSuccessResult(user);
        return result;
    }
}
