package com.geekaca.mall.controller.admin.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CarouselParam {

    @ApiModelProperty("轮播图地址")
    @NotEmpty(message = "轮播图不能为空")
    private String carouselUrl;

    @ApiModelProperty("跳转地址")
    @NotEmpty(message = "跳转地址不能为空")
    private String redirectUrl;

    @ApiModelProperty("排序值")
    @NotNull(message = "排序值不能为空")
    private Integer carouselRank;
}
