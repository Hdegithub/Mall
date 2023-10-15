package com.geekaca.mall.controller.admin.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryParam {

    private String categoryName;

    private Integer categoryRank;

    private Integer categoryLevel;

    private Long parentId;
}
