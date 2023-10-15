package com.geekaca.mall.controller.front;


import com.geekaca.mall.service.GoodsInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//api/v1
@RestController
@RequestMapping("/api/v1")
public class MallGoodsController {
    private GoodsInfoService goodsInfoService;


}
