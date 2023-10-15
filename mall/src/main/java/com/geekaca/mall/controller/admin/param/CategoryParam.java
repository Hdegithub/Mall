package com.geekaca.mall.controller.admin.param;

import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryParam implements Serializable {
    private Long categoryId;

    private String categoryName;

    private Integer categoryRank;

    private Long parentId;

    private Integer categoryLevel;
}
