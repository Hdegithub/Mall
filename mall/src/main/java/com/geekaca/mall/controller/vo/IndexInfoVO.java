  
package com.geekaca.mall.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * VO
 * Value Object 值对象 充当数据载体
 *
 * Serializable 对象序列化， 当计算机内存不足的时候，会把一些数据自动的保存到磁盘
 */
@Data
public class IndexInfoVO implements Serializable {

    @ApiModelProperty("轮播图(列表)")
    private List<CarouselVO> carousels;

    @ApiModelProperty("首页热销商品(列表)")
    private List<HotGoodsesVO> hotGoodses;
}
