package com.geekaca.mall.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.interfaces.Claim;
import com.geekaca.mall.common.ServiceResultEnum;
import com.geekaca.mall.controller.vo.MallUserAddressVO;
import com.geekaca.mall.domain.UserAddress;
import com.geekaca.mall.service.MallUserAddressService;
import com.geekaca.mall.utils.JwtUtil;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "v1", tags = "商城个人地址相关接口")
@RequestMapping("/api/v1")
@Slf4j
public class MallUserAddressController {
    @Autowired
    private MallUserAddressService mallUserAddressService;

    @GetMapping("/address")
    public Result<List<MallUserAddressVO>> addressList(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        long UserId = Long.parseLong(uid);
        return ResultGenerator.genSuccessResult(mallUserAddressService.getAddresses(UserId));
    }
    @GetMapping("/address/{addressId}")
    @ApiOperation(value = "获取收货地址详情", notes = "传参为地址id")
    public Result<MallUserAddressVO> getMallUserAddress(@PathVariable("addressId") Long addressId,
                                                              HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        long UserId = Long.parseLong(uid);
        UserAddress mallUserAddressById = mallUserAddressService.getUserAddressById(addressId);
        MallUserAddressVO mallUserAddressVO = new MallUserAddressVO();
        BeanUtil.copyProperties(mallUserAddressById, mallUserAddressVO);
        if (UserId != (mallUserAddressById.getUserId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        return ResultGenerator.genSuccessResult(mallUserAddressVO);
    }

    @GetMapping("/address/default")
    @ApiOperation(value = "获取默认收货地址", notes = "无传参")
    public Result getDefaultMallUserAddress(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        Claim idClaim = stringClaimMap.get("id");
        String uid = idClaim.asString();
        long UserId = Long.parseLong(uid);
        UserAddress userAddressById = mallUserAddressService.getMyDefaultAddressByUserId(UserId);
        return ResultGenerator.genSuccessResult(userAddressById);
    }
}
