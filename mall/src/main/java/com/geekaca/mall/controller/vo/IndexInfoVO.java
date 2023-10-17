  
package com.geekaca.mall.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * VO
 * Value Object 值对象 充当数据载体
 */
@Data
public class IndexInfoVO implements Serializable {

    @ApiModelProperty("轮播图(列表)")
    private List<CarouselVO> carousels;

    @ApiModelProperty("首页热销商品(列表)")
    private List<HotGoodsesVO> hotGoodses;
}
