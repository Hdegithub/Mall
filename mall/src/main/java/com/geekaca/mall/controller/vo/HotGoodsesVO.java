package com.geekaca.mall.controller.vo;

import lombok.Data;


@Data
public class HotGoodsesVO {
    private Long goodsId;
    private Double sellingPrice;
    private String goodsCoverImg;
    private String goodsName;
    /**
     * 配置的 新品名
     */
    private String configName;
}
