package com.geekaca.mall.config;

import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionResolver {

    /**
     * 参数格式不符合要求，会触发
     * 参数不匹配异常
     * 函数返回值：
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("------->MethodArgumentNotValidException参数异常-------- ", e);
        //接口返回一个失败的结果     后面： 获取 违反了domain类声明的哪个规则
        return ResultGenerator.genFailResult("参数异常" + e.getBindingResult().getFieldError().getDefaultMessage());
        //getDefaultMessage()会返回message信息
    }
}
