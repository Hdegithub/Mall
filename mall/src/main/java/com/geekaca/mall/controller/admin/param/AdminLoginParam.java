package com.geekaca.mall.controller.admin.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

//用来携带登陆参数
@Data
public class AdminLoginParam implements Serializable {

    /**
     * 对 用户传递过来的参数值
     * 进行校验
     */
    @NotEmpty(message = "登录名不能为空")
    private String userName;


    @NotEmpty(message = "密码不能为空")
    private String passwordMd5;
}
