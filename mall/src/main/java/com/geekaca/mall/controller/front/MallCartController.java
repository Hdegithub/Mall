package com.geekaca.mall.controller.front;

import com.auth0.jwt.interfaces.Claim;
import com.geekaca.mall.common.Constants;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.controller.front.param.SaveCartItemParam;
import com.geekaca.mall.controller.front.param.ShoppingCartItemVO;
import com.geekaca.mall.service.ShoppingCartItemService;
import com.geekaca.mall.utils.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MallCartController {
    @Autowired
    private ShoppingCartItemService cartItemService;

    @GetMapping("/shop-cart/page")
    @ApiOperation(value = "购物车列表(每页默认5条)", notes = "传参为页码")
    public Result<PageResult<List<ShoppingCartItemVO>>> cartItemPageList(Integer pageNumber, HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        long userId = Long.parseLong(uid);
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("userId", userId);
        params.put("page", pageNumber);
        params.put("limit", Constants.SHOPPING_CART_PAGE_LIMIT);
        //封装分页请求参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(cartItemService.getMyShoppingCartItems(pageUtil));
    }

    @GetMapping("/shop-cart")
    public Result<List<ShoppingCartItemVO>> cartItemList(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        long userId = Long.parseLong(uid);
        return ResultGenerator.genSuccessResult(cartItemService.getMyShoppingCartItems(userId));
    }

    @PostMapping("/shop-cart")
    @ApiOperation(value = "添加商品到购物车接口", notes = "传参为商品id、数量")
    public Result saveNewBeeMallShoppingCartItem(@RequestBody SaveCartItemParam saveCartItemParam,
                                                 HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        long UserId = Long.parseLong(uid);
        String saveResult = cartItemService.saveMallCartItem(saveCartItemParam, UserId);
        //添加成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(saveResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //添加失败
        return ResultGenerator.genFailResult(saveResult);
    }
}
