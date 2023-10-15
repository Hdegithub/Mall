package com.geekaca.mall.controller.admin.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryParam implements Serializable {

    private String categoryName;

    private Integer categoryRank;

    private Integer categoryLevel;

    private Long parentId;
}
