package com.geekaca.mall.controller.front;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.geekaca.mall.common.Constants;
import com.geekaca.mall.common.NewBeeMallException;
import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.vo.GoodsDetailVO;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.service.GoodsInfoService;
import com.geekaca.mall.utils.PageQueryUtil;
import com.geekaca.mall.utils.PageResult;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//api/v1
@RestController
@RequestMapping("/api/v1")
public class MallGoodsController {
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/goods/detail/{goodsId}")
    public Result<GoodsDetailVO> getGoodsById(@PathVariable("goodsId") Long goodsId) {
        GoodsInfo goods = goodsInfoService.getGoodsById(goodsId);
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        //集合拷贝
        BeanUtil.copyProperties(goods, goodsDetailVO);
        //图片地址
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        return ResultGenerator.genSuccessResult(goodsDetailVO);
    }

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口", notes = "根据关键字和分类id进行搜索")
    public Result search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyword,
                         @RequestParam(required = false) @ApiParam(value = "分类id") Long goodsCategoryId,
                         @RequestParam(required = false) @ApiParam(value = "排序关键字") String orderBy,
                         @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber
    ) {
        Map params = new HashMap();
//        FrontPageVo frontPageVo = new FrontPageVo();
        if (goodsCategoryId == null && !StringUtils.hasText(keyword)) {
            NewBeeMallException.fail("搜索参数错误");
        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("goodsCategoryId", goodsCategoryId);
        params.put("page", pageNumber);
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        params.put("keyword", keyword);
        params.put("orderBy", orderBy);
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult pageResult = goodsInfoService.searchGoods(pageUtil);
        /**
         * 想要调用 其他应用的接口，获取数据
         *  根据id 获取某个商品详情
         *
         * GET http://localhost:28020/api/v1/goods/detail/10003
         *
         *  比如：调用网上第三方http服务
         *
         *  短信服务
         *  支付类
         */
//        String url = "http://localhost:28020/api/v1/goods/detail/10003";
//        ResponseEntity<Result> forEntity = restTemplate.getForEntity(url, Result.class);
//        Result result = forEntity.getBody();
//        Map goodsMap = (Map) result.getData();
//        System.out.println(goodsMap);
        postForLogin();
        return ResultGenerator.genSuccessResult(pageResult);


    }

    private boolean postForLogin() {
        String url = "http://localhost:28020/api/v1//user/login";
//        // 请求头设置,x-www-form-urlencoded格式的数据
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //以POST JSON方式提交
        //提交参数设置
        MallUserLoginParam userLoginParam = new MallUserLoginParam();
        userLoginParam.setLoginName("guest");
        userLoginParam.setPasswordMd5(SecureUtil.md5("123456"));

        // 组装请求体
        /**
         * url
         * userLoginParam： 携带参数的对象
         * Result.class: 设置 返回值 用什么类型接收（前提  对方接口返回的数据格式 JSON 能够和Result属性对应）
         */
        Result result = restTemplate.postForObject(url, userLoginParam, Result.class);
        System.out.println("getResultCode: " + result.getResultCode());
        System.out.println("getData:" + result.getData());
        if ("200".equals(result.getResultCode())) {
            return true;
        }
        return false;
    }
}
